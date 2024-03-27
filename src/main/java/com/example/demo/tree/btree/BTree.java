package com.example.demo.tree.btree;

import java.util.*;

/**
 * B 树的简单实现
 *
 * @param <K>
 * @param <V>
 */
public class BTree <K,V>{

    //B 树的阶数
    private static int M = 2;

    //b 树的根节点
    private BTreeNode<K,V> root;

    //非根节点中最小的键值对（根节点则是  [1,M-1]）
    private int minKeySize = (M - 1) >> 1;

    //所有节点中最大键值对
    private int maxKeySize = M - 1;

    //键的比较器
    private Comparator<K> kComparator;

    public BTree(){
        root = new BTreeNode<>();
        root.setLeaf(true);

    }

    public BTree(int m){
        this();
        M = m;
        minKeySize = (M - 1) >> 1;;
        maxKeySize = M - 1;
    }
    public BTree(Comparator<K> kComparator){
        root = new BTreeNode<>(kComparator);
        root.setLeaf(true);
        this.kComparator = kComparator;
    }
    public BTree(Comparator<K> kComparator, int m){
        this(kComparator);
        M = m;
        minKeySize = (M - 1) >> 1;;
        maxKeySize = m - 1;
    }

    public int compare(K k1, K k2){
        return kComparator == null ? ((Comparable<K>) k1).compareTo(k2) : kComparator.compare(k1,k2);
    }

    /**
     * 搜索指定的键
     *
     * @param key
     * @return
     */
    public V findValueByKey(K key){
        return findValueByKey(root,key);
    }

    /**
     * 在 node 查找键位key的value
     *
     * @param node
     * @param key
     * @return
     */
    public V findValueByKey(BTreeNode<K,V> node, K key){
        SearchResult<V> result = node.searchKey(key);
        if (result.isExist()){
            return result.getValue();
        }else {
            if (node.isLeaf()){
                return null;
            }else {
                return findValueByKey(node.childAt(result.getIndex()),key);
            }
        }
    }

    /**
     * 添加 元素
     * @param k
     * @param v
     * @return
     */
    public V put(K k, V v){
        BTreeNode<K,V> node = root;
        //第一次添加元素
        if (node == null){
            node = new BTreeNode<>();
            node.entries.add(new Entry<>(k,v));
            return v;
        }
        //查找要添加到哪个 BTreeNode 中
        BTreeNode<K, V> curNode = findBTreeNode(node, k);
        //插入
        V rv = curNode.insertEntry(new Entry<>(k, v));
        //处理可能发生上溢的情况
        upOverFlow(curNode);
        return rv;
    }

    /**
     * 删除 key 为 k 的关键字
     *  1，删除的关键字所在节点为叶子节点，直接删除即可
     *  2.删除的关键字所在节点为非叶子结点，找到前驱或后继节点覆盖掉所需删除的关键字的 value，再将前驱或后继节点删除（我们这里找前驱节点）
     *  3.无论哪种情况，删除后都需考虑下溢的情况
     *
     * @param k
     * @return
     */
    public V remove(K k){
        BTreeNode<K,V> node = root;
        //k所在node
        BTreeNode<K, V> hasNode = findHasNode(root, k);
        //e未找到
        if (hasNode == null) return null;
        //查找 hasNode 中是否包含 entry的key 为 k
        SearchResult<V> result = hasNode.searchKey(k);
        //未找到
        if (!result.exist) return null;

        //1.删除关键字所在节点为叶子节点
        if (hasNode.isLeaf()){
            removeLeaf(hasNode,result.index);
            //处理可能下溢的情况
            downOverFlow(hasNode);
        }else {
            //2.删除关键字所在节点为非叶子节点
            //  (1).查找被删除节点的前驱节点
            BTreeNode<K, V> predecessorNode = predecessorNode(hasNode, k);
            //  (2).用前驱节点替换被删除节点
            hasNode.entries.set(result.index, predecessorNode.entries.get(predecessorNode.entries.size() - 1));
            //  (3).删除前驱节点
            removeLeaf(predecessorNode,predecessorNode.entries.size() - 1);
            //  (4).处理可能下溢的情况
            downOverFlow(hasNode);
        }
        //返回被删除节点 value
        return result.value;
    }

    /**
     * 解决 node 节点的下溢情况
     *  1.node为根结点， 删除后entries 为空，发生下溢
     *  2.node不为根节点。删除后  entries < minKeySize，发生下溢
     *  （1）.如果node的左兄弟节点数量 大于 minKeySize + 1，可向其借一个，进行右旋转
     *  （2）.如果node的右兄弟节点数量 大于 minKeySize + 1，可向其借一个，进行左旋转
     *  （3）.如果左右兄弟节点都无法借，则向父类借借一个，并将node 和兄弟节点进行合并生成新的节点 (我这里优先和左兄弟合并)
     *  （4）.如果父类节点被借了一个后，仍需判断父类节点是否发生下溢
     *  （5）.如果父类节点是根节点，且发生下溢了，则合并后的节点为新的根节点
     * @param node
     */
    public void downOverFlow(BTreeNode<K,V> node){
        //1.node为根结点， 删除后entries 为空，发生下溢
        //（5）.如果父类节点是根节点，且发生下溢了，则合并后的节点为新的根节点
        if (node == root && node.entries.size() < 1){
            if (node.children.size() > 0){
                root = node.children.get(0);
            }
            return;
        }
        //2.node不为根节点。删除后  entries < minKeySize，发生下溢
        if (node.entries.size() < minKeySize){
            //（1）.如果node的左兄弟节点数量 大于 minKeySize + 1，可向其借一个，进行右旋转
            //查找node节点 在父节点children 的 索引位
            int curIndex = childIndex(node.parent, node);
            List<BTreeNode<K, V>> pChildren = node.parent.children;
            //可向左兄弟借
            if (curIndex > 0 && pChildren.get(curIndex - 1).entries.size() > minKeySize){
                //右旋转
                rightRotate(node,curIndex - 1);
                return;
            }
            //（2）.如果node的右兄弟节点数量 大于 minKeySize + 1，可向其借一个，进行左旋转
            //可向右兄弟借
            if (curIndex < pChildren.size() - 1 && pChildren.get(curIndex + 1).entries.size() > minKeySize){
                //左旋转
                leftRotate(node,curIndex);
                return;
            }
            //（3）.如果左右兄弟节点都无法借，则向父类借借一个，并将node 和兄弟节点进行合并生成新的节点
            if (curIndex > 0){
                //左兄弟
                BTreeNode<K, V> leftNode = pChildren.get(curIndex - 1);
                //合并了，则需要将 leftNode 从父节点的子节点中移除
                pChildren.remove(curIndex - 1);
                //被借用的父节点的关键字
                Entry<K, V> pEntry = node.parent.entries.get(curIndex - 1);
                node.parent.entries.remove(curIndex-1);
                //合并成新的节点,我这里采用以左兄弟节点为基础，进行合并
                leftNode.entries.add(pEntry);
                leftNode.entries.addAll(node.entries);
                leftNode.children.addAll(node.children);
            }else {
                //没有左兄弟，只能和右兄弟合并了
                BTreeNode<K, V> rightNode = pChildren.get(curIndex + 1);
                //合并了，则需要将 rightNode 从父节点的子节点中移除
                pChildren.remove(curIndex + 1);
                //被借用的父节点的关键字
                Entry<K, V> pEntry = node.parent.entries.get(curIndex);
                node.parent.entries.remove(curIndex);
                //合并成新的节点,我这里采用以当前节点为基础，进行合并
                node.entries.add(pEntry);
                node.entries.addAll(rightNode.entries);
                node.children.addAll(rightNode.children);
            }
            //（4）.如果父类节点被借了一个后，仍需判断父类节点是否发生下溢
            downOverFlow(node.parent);
        }
    }

    /**
     * 右旋转
     *
     * @param node 有节点
     * @param index 参与旋转的关键字在父类entries的索引位
     */
    public void rightRotate(BTreeNode<K,V> node, int index){
        //父节点
        BTreeNode<K, V> parent = node.parent;
        //父节点参与旋转的entry
        Entry<K, V> pEntry = parent.entries.get(index);
        //参与旋转的左兄弟节点中的关键字（其实就是左兄弟最后一位关键字）
        BTreeNode<K, V> leftNode = parent.children.get(index);
        Entry<K, V> leftEntry = leftNode.entries.get(leftNode.entries.size() - 1);

        //1.向当前节点的最左端添加父节点的关键字
        node.entries.add(0,pEntry);
        //2.将左兄弟最右边的关键字替换掉父类关键字
        parent.entries.set(index,leftEntry);
        //3.将左兄弟最右边的子节点添加到 node 的children的最左边
        if (leftNode.children.size() > 0){
            //参与旋转的左兄弟节点中的关键字的右节点（其实就是左节点的最后一个child）
            BTreeNode<K, V> leftChild = leftNode.children.get(leftNode.children.size() - 1);
            node.children.add(0,leftChild);
            //4.移除左兄弟参数旋转的关键字和子节点
        }
        leftNode.entries.remove(leftNode.entries.size() - 1);
    }

    /**
     * 左旋转
     *
     * @param node 左节点
     * @param index 参与旋转的关键字在父类entries的索引位
     */
    public void leftRotate(BTreeNode<K,V> node, int index){
        //父节点
        BTreeNode<K, V> parent = node.parent;
        //父节点参与旋转的entry
        Entry<K, V> pEntry = parent.entries.get(index);
        //参与旋转的右兄弟节点中的关键字（其实就是右兄弟第一位关键字）
        BTreeNode<K, V> rightNode = parent.children.get(index + 1);
        Entry<K, V> rightEntry = rightNode.entries.get(0);

        //1.向当前节点的最右端添加父节点的关键字
        node.entries.add(pEntry);
        //2.将右兄弟最右边的关键字替换掉父类关键字
        parent.entries.set(index,rightEntry);
        //3.将右兄弟最右边的子节点添加到 node 的children的最左边
        if (rightNode.children.size() > 0){
            //参与旋转的右兄弟节点中的关键字的左节点（其实就是右节点的第一位child）
            BTreeNode<K, V> rightChild = rightNode.children.get(0);
            node.children.add(rightChild);
            //4.移除右兄弟参数旋转的关键字和子节点
            rightNode.children.remove(0);
        }
        rightNode.entries.remove(0);
    }

    /**
     * node 节点为叶子节点，删除 node.entries index 处的关键字
     *
     * @param node 被删除关键字所在节点
     * @param index 被删除关键字所在索引
     */
    public void removeLeaf(BTreeNode<K,V> node, int index){
        node.entries.remove(index);
    }

    /**
     * 上溢
     * 上溢需要将
     *      1.将当前节点中所有的键值对中的中间的那个键值对转移到父节点（BTreeNode）的键值对中，
     *      2.并且需要当前节点（TreeNode）分裂成两个节点（TreeNode）
     *      3.两个节点中的键值对 以上溢的键值对为分界线，分别给予刚分裂成两份的节点中（BTreeNode)中。
     *      4.当前节点分裂成两个了，且键值对也分裂了，那么当前节点的 children 也要进行分裂
     */
    private void upOverFlow(BTreeNode<K,V> node){
        //当前节点的所有键值对
        List<Entry<K, V>> entries = node.entries;
        //当前节点的键值对数量
        int size = entries.size();
        //上溢结束
        if (size <= maxKeySize) return;
        BTreeNode<K, V> parent = node.parent;
        //如果当前节点已经为父类节点
        if (parent == null){
            parent = new BTreeNode<>();
            parent.setLeaf(false);
            root = parent;
        }
        //当前节点进行分裂
        splitNode(node,parent);
        //处理父类节点可能上溢的情况
        upOverFlow(parent);
    }

    /**
     * 将 node 进行分裂成两个node，leftNode 和 rightNode
     *  1.当前node不是叶子节点，将当前节点children中parent分别更新为leftNode和rightNode，以中间节点为分界线
     *  2.当前节点是叶子结点，则无需更新children
     *
     * @param node 需要拆分的节点
     * @param parent node 的父节点
     */
    private Entry<K, V> splitNode(BTreeNode<K,V> node,BTreeNode<K,V> parent){
        //将当前node 以中间节点为分界线进行分裂
        List<Entry<K, V>> entries = node.entries;
        int size = entries.size();

        //分裂成两个节点
        BTreeNode<K, V> leftNode = new BTreeNode<>(parent,node.leaf);
        BTreeNode<K, V> rightNode = new BTreeNode<>(parent,node.leaf);
        //剔除当前节点
        node.parent = null;
        //将当前节点的entries 分割到两个新的节点中,如果是偶数，我选择的是靠左边的节点
        int mid = (size - 1) >> 1;
        for (int i = 0; i < mid; i++) {
            leftNode.entries.add(entries.get(i));
        }
        for (int i = mid + 1; i < size; i++) {
            rightNode.entries.add(entries.get(i));
        }
        //发生上溢的节点
        Entry<K, V> upEntry = entries.get(mid);

        //将拆分后的两个节点添加到父类的子节点中
        //父类节点是新建的，无子类节点,直接添加即可
        if (parent.children.size() == 0){
            parent.children.add(leftNode);
            parent.children.add(rightNode);
            parent.entries.add(upEntry);
        }else {
            //1.未拆分前的节点在 父类节点的子节点中的位置
            int index = childIndex(parent, node);
            //2.向指定位置插入新的节点 从左到右的顺序应该是  left -> right
            parent.children.remove(index);
            parent.children.add(index,rightNode);
            parent.children.add(index,leftNode);
            parent.entries.add(index,upEntry);
        }
        //如果当前节点有子类节点（发生在连续上溢的情况），由于当前节点发生了分裂，原节点被删除，则需要更新当前节点的和子类节点
        //1. 更新分裂后的节点的子类节点
        //2. 更新子类节点的父类节点
        if (node.children.size() > 0){
            for (int i = 0; i <= mid; i++) {
                BTreeNode<K, V> child = node.children.get(i);
                child.parent = leftNode;
                leftNode.addChild(child);
            }
            for (int i = mid + 1; i < node.children.size(); i++) {
                BTreeNode<K, V> child = node.children.get(i);
                child.parent = rightNode;
                rightNode.addChild(child);
            }
        }
        //返回发生拆分的中间节点
        return upEntry;
    }

    /**
     * 查找k应该插入的 BTreeNode，因为是插入，所以必然是返回叶子节点
     *
     * @param node
     * @param k
     * @return
     */
    private BTreeNode<K,V> findBTreeNode(BTreeNode<K,V> node ,K k){
        if (node.leaf){
            return node;
        }else {
            //判断应该去哪个子树中去查找合适的位置
            List<Entry<K, V>> entries = node.entries;
            int n = entries.size();
            for (int i = 0; i < n; i++) {
                if (compare(entries.get(i).key, k) >= 0){
                    return findBTreeNode(node.children.get(i),k);
                }
            }
            //插入的k比当前节点中所有键值对的k都大，则去最后的child去查找
            return findBTreeNode(node.children.get(n), k);
        }
    }

    /**
     * 查找key为k的所在TreeNode
     *
     * @param node
     * @param k
     * @return
     */
    public BTreeNode<K,V> findHasNode(BTreeNode<K,V> node, K k){
        for (int i = 0; i < node.entries.size(); i++) {
            Entry<K, V> entry = node.entries.get(i);
            int compare = compare(entry.key, k);
            if (compare == 0) return node;
            if (compare > 0){
                //如果当前是叶子结点，则没有子节点了，未找到
                if (node.isLeaf()) return null;
                return findHasNode(node.children.get(i),k);
            }
        }
        //如果当前是叶子结点，则没有子节点了，未找到
        if (node.leaf) return null;
        return findHasNode( node.children.get(node.children.size() - 1),k);
    }

    /**
     * 在 node 的children 中查找 child 所在的位置并返回
     *
     * @param node
     * @param child
     * @return
     */
    public int childIndex(BTreeNode<K,V> node, BTreeNode<K,V> child){
        for (int i = 0; i < node.children.size(); i++) {
            if (node.children.get(i).equals(child)) return i;
        }
        return -1;
    }

    /**
     * 从 node 开始，查找k 的前驱关键字所在节点
     * 这里为什么不直接返回前驱关键字呢，因为我们需要拿到前驱关键字所在的节点进行后续操作
     *
     * @param node
     * @param k
     * @return
     */
    public BTreeNode<K,V> predecessorNode(BTreeNode<K,V> node,K k){
        while (!node.isLeaf()){
            return predecessorNode(node.children.get(node.children.size() - 1),k);
        }
        return node;
    }

    public void print(){
        LinkedList<BTreeNode<K,V>> queue = new LinkedList<>();
        BTreeNode<K,V> node = root;
        queue.addFirst(node);
        int cen = 1;
        int size = 0;
        while (!queue.isEmpty()){
            StringBuilder builder = new StringBuilder();
            List<BTreeNode<K,V>> next = new ArrayList<>();
            while (!queue.isEmpty()){
                BTreeNode<K, V> cur = queue.pop();
                next.addAll(cur.children);
                for (Entry<K, V> entry : cur.entries) {
                    size ++;
                    builder.append("K").append(entry.key).append(" || V:").append(entry.value).append(" || P:").append(cur.parent).append(" ~~~~ ");
                }
            }
            System.out.println("第" + cen +"层"+"::: " + builder);
            cen ++;
            queue.addAll(next);
        }
        System.out.println(size);
    }


    /**
     * 用于存储元素的键值对
     *
     * @param <K>   用来排序的键，并指向我们需要存储的value
     * @param <V>   实际用来存储我们添加的值的
     */
    private static class Entry<K,V> {
        private K key;
        private V value;

        public Entry(K k, V v){
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * 我们查询到的结果同意分装到该对象中
     *
     * @param <V>
     */
    public static class SearchResult<V> {
        //查询是对象是否存在 true：存在  false：不存在
        private boolean exist;
        //查询到的节点所在位置 或未查找到时，他应该所在的位置
        private int index;
        //查询到的值
        private V value;

        public SearchResult(boolean exist, int index, V value){
            this.exist = exist;
            this.index = index;
            this.value = value;
        }

        public boolean isExist(){
            return exist;
        }

        public int getIndex(){
            return index;
        }

        public V getValue() {
            return value;
        }
    }


    /**
     * B 树中的节点
     *
     * @param <K>
     * @param <V>
     */
    public static class BTreeNode<K,V>{

        /**
         * 节点的项，也有很多称呼为关键字。按照 键 进行排序
         */
        private List<Entry<K,V>> entries;

        /**
         * 父节点
         */
        private BTreeNode<K,V> parent;
        /**
         * 子节点
         */
        private List<BTreeNode<K,V>> children;

        /**
         * 是否为叶子节点
         */
        private boolean leaf;

        /**
         * 键的比较器
         */
        private Comparator<K> kComparator;

        private BTreeNode(){
            this.entries = new ArrayList<>();
            this.children = new ArrayList<>();
            this.parent = null;
            this.leaf = true;
        }

        private BTreeNode(BTreeNode<K,V> parent){
            this();
            this.parent = parent;
        }
        private BTreeNode(BTreeNode<K,V> parent, boolean leaf){
            this();
            this.parent = parent;
            this.leaf = leaf;
        }
        public BTreeNode(Comparator<K> kComparator){
            this();
            this.kComparator = kComparator;
        }

        public boolean isLeaf(){
            return leaf;
        }

        public void setLeaf(boolean leaf){
            this.leaf = leaf;
        }

        /**
         * 返回关键字的个数
         */
        public int size(){
            return entries.size();
        }

        public int compare(K k1, K k2){
            return kComparator == null ? ((Comparable<K>) k1).compareTo(k2) : kComparator.compare(k1,k2);
        }

        /**
         * 查找键为 K 的值,采用二分查找
         *   1.找到了，返回找到的Entry的索引位
         *   2.没找到，返回传入的key应该插入的索引位
         *
         * @param key
         * @return
         */
        public SearchResult<V> searchKey(K key){
            int low = 0;
            int high = entries.size() - 1;
            int mid = 0;
            while (low <= high){
                mid = low + (high - low) / 2;
                Entry<K, V> entry = entries.get(mid);
                int compare = compare(entry.getKey(), key);
                if (compare == 0){
                    break;
                }else if (compare > 0){
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }
            boolean result = false;
            int index = low;
            V value = null;
            //查找成功
            if (low <= high){
                result = true;
                index = mid;
                value = entries.get(index).getValue();
            }
            return new SearchResult<>(result,index,value);
        }

        /**
         * 向当前节点 插入 entry
         *
         * @param entry
         */
        public V insertEntry(Entry<K,V> entry){
            SearchResult<V> result = searchKey(entry.key);
            if (!result.exist){
                //查找应该插入的位置
                insertEntry(entry,result.index);
                return entry.value;
            }
            //覆盖 value
            entries.get(result.index).value = entry.value;
            return result.value;
        }

        /**
         * 向 当前 节点的键值对中在index处插入entry
         *
         * @param entry
         * @param index
         */
        public void insertEntry(Entry<K,V> entry, int index){
            entries.add(index,entry);
        }
        /**
         * 返回给定位置的当前节点的子节点
         *
         * @param index
         * @return
         */
        public BTreeNode<K,V> childAt(int index){
            if (isLeaf()){
                throw new UnsupportedOperationException("Leaf node doesn't have children.");
            }
            return children.get(index);
        }
        /**
         * 将给定的子节点追加到当前节点的自己点末尾
         *
         * @param child
         */
        public void addChild(BTreeNode<K,V> child){
            children.add(child);
        }
    }
}
