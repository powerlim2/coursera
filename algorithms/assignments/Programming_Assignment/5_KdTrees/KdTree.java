public class KdTree {

    private static final boolean DEBUG = false;
    private static final boolean PROFILE = false;
    private int nodes = 0;
    private Node root;
    private double bestsofar = 1;

    public KdTree() {                            // construct an empty set of points
        root = null;
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;        // left/bottom.
        private Node rt;        // right/top subtree

        public Node(Point2D ppoint, RectHV prect)
        {
            p = ppoint;
            rect = prect;
        }

        public Point2D p() { return p; }
        public Node    lb() { return lb; }
        public Node    rt() { return rt; }

        public int compareTo(Point2D pp, boolean vertical)
        {
            if (vertical)
                return Point2D.X_ORDER.compare(p, pp);
            else
                return Point2D.Y_ORDER.compare(p, pp);
        }
    }

    public boolean isEmpty()                        // is the set empty?
    {
        return nodes == 0;
    }
    public int size()                               // number of points in the set
    {
        return nodes;
    }


    public boolean contains(Point2D p)
    // does the set contain the point p?
    {
        return get(root, p, true) != null;
    }

    private Node get(Node x, Point2D p, boolean vertical)
    {
        if (x == null) return null;

        int cmp = x.compareTo(p, vertical);
        if (cmp > 0)      return get(x.lb, p, !vertical);
        else if (cmp < 0) return get(x.rt, p, !vertical);
        else {
            if (!x.p.equals(p))
                return get(x.rt, p, !vertical);
            else
                return x;
        }
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p)
    {
        // if (!contains(p))
        root = put(root, root, false, p, true);
    }
    private Node newLBNode(Point2D p, Node parent, boolean vertical) {
        //        StdOut.printf("parent.p: %s rect:%s   p:%s vert:%b\n",
        // parent.p, parent.rect, p, vertical);
        if (vertical)
            return new Node(p, new RectHV(Math.min(parent.rect.xmax(), p.x()),
                    parent.rect.ymin(),
                    Math.max(p.x(), parent.rect.xmin()),
                    parent.rect.ymax()));
        else
            return new Node(p, new RectHV(parent.rect.xmin(),
                    Math.min(parent.rect.ymin(), p.y()),
                    Math.max(p.x(), parent.p.x()),
                    Math.max(p.y(), parent.p.y())));
    }

    private Node newRTNode(Point2D p, Node parent, boolean vertical) {
        if (vertical)
            return new Node(p, new RectHV(Math.min(p.x(), parent.rect.xmax()),
                    parent.rect.ymin(),
                    Math.max(p.x(), parent.rect.xmax()),
                    parent.rect.ymax()));
        else
            return new Node(p, new RectHV(parent.p.x(),
                    Math.min(parent.rect.ymin(), p.y()),
                    parent.rect.xmax(),
                    Math.max(parent.rect.ymax(), p.y())));
    }

    private Node put(Node x, Node parent, boolean lb, Point2D p, boolean vertical) {
        //        StdOut.printf("put\n");
        // add Rect later...
        if (x == null)
        {
            nodes++;
            if (parent == null)   {  // a root. {
                //                    StdOut.printf("create a root \n");
                return new Node(p, new RectHV(0, 0, 1, 1));
            } else {
                if (lb)
                    return newLBNode(p, parent, vertical);
                else
                    return newRTNode(p, parent, vertical);
            }
        }

        int cmp = x.compareTo(p, vertical);

        if (cmp > 0)            // left.
            x.lb = put(x.lb, x, true, p, !vertical);
        else if (cmp < 0)                    // bigger or equal go right.
            x.rt = put(x.rt, x, false, p, !vertical);
        else {
            if (!x.p.equals(p))
                x.rt = put(x.rt, x, false, p, !vertical);
            // else, it was in tree, do noting...
        }
        return x;
    }

    public void draw()
    // draw all of the points to standard draw
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);

        drawTree(root, null, true);
    }

    private void drawTree(Node x, Node parent, boolean vertical)
    {
        if (x == null)
            return;

        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), x.rect.ymax(), x.p.x(), x.rect.ymin());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }

        drawTree(x.lb, x, !vertical);
        drawTree(x.rt, x, !vertical);
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)
    {
        SET<Point2D> set = new SET<Point2D>();

        if (DEBUG) StdOut.println("----");
        range(set, rect, root, true);
        if (DEBUG) StdOut.println("----");

        return set;
    }

    private boolean intersect(double min, double max, double p)
    {
        if (p >= min && p <= max)
            return true;
        return false;
    }

    private void range(SET<Point2D> set, RectHV rect,
                       Node node, boolean vertical)
    {

        boolean contain;
        if (node == null)
            return;

        if (DEBUG)
            StdOut.printf("search p:%s\n", node.p);
        contain = rect.contains(node.p);
        if (contain)
            set.add(node.p);

        // If contain, search both side.
        if (contain) {
            range(set, rect, node.lb, !vertical);
            range(set, rect, node.rt, !vertical);
        } else {
            if (vertical) {
                // If intersect, seach both side.
                if (intersect(rect.xmin(), rect.xmax(), node.p.x())) {
                    range(set, rect, node.lb, !vertical);
                    range(set, rect, node.rt, !vertical);
                } else {
                    if (rect.xmax() <= node.p.x())
                        range(set, rect, node.lb, !vertical);
                    if (rect.xmin() >= node.p.x())
                        range(set, rect, node.rt, !vertical);
                }
            } else {
                // If intersect, search both side
                if (intersect(rect.ymin(), rect.ymax(), node.p.y())) {
                    // if (DEBUG)
                    //     StdOut.printf("point:%s intersect %s search both\n",
                    //                   node.p, rect);
                    range(set, rect, node.lb, !vertical);
                    range(set, rect, node.rt, !vertical);
                } else {
                    if (rect.ymax() <= node.p.y())
                        range(set, rect, node.lb, !vertical);
                    if (rect.ymin() >= node.p.y())
                        range(set, rect, node.rt, !vertical);
                }
            }
        }
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p)
    {

        Stopwatch timer;
        Point2D ret;

        if (nodes == 0)
            return null;
        if (p == null)
            throw new RuntimeException("");

        if (DEBUG) StdOut.println("======");
        if (DEBUG)              dumpTreeLevel();

        if (PROFILE)
            timer = new Stopwatch();

        bestsofar = root.p.distanceSquaredTo(p);
        ret = findNearest(root, p, root.p.distanceSquaredTo(p), true);
        if (PROFILE)
            StdOut.printf("find %s takes %f ms.\n", p,  timer.elapsedTime());
        return ret;
    }

    private void updateBestIfNeed(double a)
    {
        if (a < bestsofar)
            bestsofar = a;
    }

    private Point2D findNearest(Node node, Point2D p,
                                double best,  boolean vertical)
    {
        if (node == null)
            return null;


        Point2D p2 = null;
        double nodeToP = p.distanceSquaredTo(node.p);
        double orignal = nodeToP;

        updateBestIfNeed(nodeToP);

        if (nodeToP == 0)
            return node.p;

        if (best < nodeToP)
            nodeToP = best;

        // if (DEBUG)

        // StdOut.printf("Find N: P: %s distance:%f\n", node.p, nodeToP);

        // Dummy call...
        p.x();
        p.y();


        if ((vertical && Point2D.X_ORDER.compare(p, node.p) < 0)
                || (!vertical && Point2D.Y_ORDER.compare(p, node.p) <= 0)) {
            double newd = 99.0;
            Point2D p3 = null;

            p2 = findNearest(node.lb, p, nodeToP, !vertical);
            if (p2 != null)  {
                boolean go = true;
                newd = p2.distanceSquaredTo(p);

                if (newd < nodeToP) {
                    nodeToP = newd;
                    //node.rect.distanceSquaredTo(p);
                    // Always needs search right ?
                    if (node != root
                            && (node.rt == null
                            || node.rt.p.distanceSquaredTo(p) > bestsofar))
                        go = false;
                }
                if (node == root || go) {
                    p3 = findNearest(node.rt, p, nodeToP, !vertical);
                    double p3d;
                    if (p3 != null &&  p3.distanceSquaredTo(p) < nodeToP)
                        p2 = p3;
                }
            }


        } else if ((vertical && Point2D.X_ORDER.compare(p, node.p) >= 0)
                || (!vertical && Point2D.Y_ORDER.compare(p, node.p) > 0)) {

            Point2D p3 = null;
            double newd = 99.0;

            p2 = findNearest(node.rt, p, nodeToP, !vertical);

            // Must update the neast to children.
            if (p2 != null) {
                boolean go = true;
                newd = p2.distanceSquaredTo(p);

                if (newd < nodeToP) {
                    nodeToP = newd;
                    // Root is a special case, it must be search both
                    // side....

                    if (node != root
                            && (node.lb == null
                            || node.lb.p.distanceSquaredTo(p) > bestsofar))
                        go = false;
                }
                if (node == root || go) {
                    p3 = findNearest(node.lb, p, nodeToP, !vertical);
                    double p3d;
                    if (p3 != null && p3.distanceSquaredTo(p) < nodeToP)
                        p2 = p3;
                }
            }
        }

        if (nodeToP < orignal)
            return p2;
        else
            return node.p;
    }

    private void dumpTreeLevel()
    {
        for (Point2D p: this.levelOrder()) {
            StdOut.println(p + "" + this.get(root, p, true));
        }
    }

    private Iterable<Point2D> levelOrder() {
        Queue<Point2D> keys = new Queue<Point2D>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.p);
            queue.enqueue(x.lb);
            queue.enqueue(x.rt);
        }
        return keys;
    }

}