package com.algorithm;

import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
class B {
    public void test() {
        System.out.println("okB");
    }
}
class A extends B {
    public void test() {
        System.out.println("okA");
    }
}
class Generic {
    public void test(List<B> list) {
        B s = (B) list.get(0);
        System.out.println("ok");
    }
}
public class Main {
    public static void main(String[] args) {
        List<? extends B> list = new ArrayList<>();
        List<A> l = (List<A>) list;
        List<A> s = new ArrayList<>();
        s.add(new A());
        List<? extends B> lis = s;
        s.get(0).test();
//        Object[] o = new Object[10];
////        o[0] = 2;
//        String[] s = (String[])o;
////        System.out.println(s[0]);
//
//        B[] b = new B[10];
//        A[] a = (A[])b;

        //数组也是可以向上转型 再转回来， 不能直接向下转型
        // B是父类
//        A[] a = new A[10];
//        a[0] = new A();
//        System.out.println(a.getClass().getTypeName()); //com.algorithm.A[]
//
//        B[] b = a;
//        b[0].test(); //ok
//        System.out.println(b.getClass().getTypeName()); //com.algorithm.A[]
//
//        a = (A[])b;
//        a[0].test(); //ok
//        System.out.println(a.getClass().getTypeName()); //com.algorithm.A[]
//
//        A aa = new A();
//        System.out.println(aa.getClass().getTypeName());
//        B bb = aa;
//        System.out.println(bb.getClass().getTypeName());

//        Object[] a = { 1, 2, 3 };
//        String[] b = { "q", "a", "f" };
//        Object[] os = (Object[]) b; //向上转型bai，可以输出，没有问题
//        System.out.println(Arrays.toString(os));
//        System.out.println("------------");
//        String[] as = new String[a.length]; //向下强转du，编译没有任何问题，运行报错？？？
//        for (int i = 0; i < as.length; i++)
//        {
//            as[i] = (String)a[i];
//        }
//        System.out.println(Arrays.toString(as));


//        List<A> l = new ArrayList<A>();
//        l = (List<? extends B>) l;

//        l.add(new A());
//        g.test(l);


    }
//    public static void main(String[] args) {
//        List<Object> list = new ArrayList<>();
//        list.add("ss");
//        System.out.println(list.get(0));
//        int a = -3;
//        System.out.println("二进制输出" + Integer.toBinaryString(a));
//        a = a << 2;
//        System.out.println("二进制输出" + Integer.toBinaryString(a));
//        System.out.println("二进制输出" + Integer.toBinaryString(1837591841));
//        System.out.println("二进制输出" + Integer.toBinaryString(1837591832));
//        System.out.println("二进制输出" + Integer.toBinaryString((((~(1 << 5) & 1837591841) >> 5) << 5) | 48));
//    }
}
//abstract class a {
////    int aa = 0;
////}
////
////class b extends a {
////    int aa = 1;
////    public void p() {
////        System.out.println(aa);
////    }
////}
//        System.out.println(i);
//        Main m = new Main();
//        System.out.println(m.j);
//        System.out.println(m.getClass().getDeclaredFields());
//        Iterator
//        CopyOnWriteArrayList
//        int[][] points = new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}};
//        int[][] points = new int[][]{{-2,-1},{2,1}};
//        Arrays.sort(points, (a, b) -> (a[0] == b[0]? a[1] - b[1]: a[0] - b[0]));
//        System.out.println(Arrays.toString(points[0]));

//        List<Integer> test = Arrays.asList(new Integer[] {2, 2, 3});

//    }
//    Thread
//    static ReentrantLock lock;
//    int a;
//    static int[] arr = new int[] {1, 2, 3, 4, 5};
//    public static void main(String[] args) {
//        lock = new ReentrantLock();
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ReentrantLock lock = Main.lock;
//                lock.lock();
//
//                int[] elements =  Main.arr;
//
//                elements[2] = 8;
//                System.out.println(Arrays.toString(elements));
//                lock.unlock();
//            }
//        });
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int[] elements = Main.arr;
//                System.out.println(Arrays.toString(elements));
//            }
//        });
//        t1.start();
//        t2.start();
//    }
//            ThreadLocal
//        ReentrantLock;
//        AtomicInteger a
//        PriorityQueue
//        CountDownLatch
//        CyclicBarrier
//        Semaphore
//        ReentrantReadWriteLock
//        ArrayBlockingQueue
//        SynchronousQueue
//        ExecutorService
//        ExecutorCompletionService
//        ThreadPoolExecutor
//        ConcurrentHashMap
//        Unsafe.compareAndSwapInt

//class A {
//    final Integer a;
//    A(int a) {
//        this.a = a;
//    }
//}
