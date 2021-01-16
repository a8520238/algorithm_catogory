package com.algorithm.多线程;

/*
* 我们提供了一个类：

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
三个不同的线程将会共用一个 Foo 实例。

线程 A 将会调用 first() 方法
线程 B 将会调用 second() 方法
线程 C 将会调用 third() 方法
请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。

 

示例 1:

输入: [1,2,3]
输出: "firstsecondthird"
解释:
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
正确的输出是 "firstsecondthird"。
* */

//使用原子类
// class Foo {

//     private AtomicInteger firstJobDone = new AtomicInteger(0);
//     private AtomicInteger secondJobDone = new AtomicInteger(0);

//     public Foo() {
//     }

//     public void first(Runnable printFirst) throws InterruptedException {

//         // printFirst.run() outputs "first". Do not change or remove this line.
//         printFirst.run();
//         firstJobDone.incrementAndGet();
//     }

//     public void second(Runnable printSecond) throws InterruptedException {
//         while (firstJobDone.get() != 1) {

//         }
//         // printSecond.run() outputs "second". Do not change or remove this line.
//         printSecond.run();
//         secondJobDone.incrementAndGet();
//     }

//     public void third(Runnable printThird) throws InterruptedException {

//         // printThird.run() outputs "third". Do not change or remove this line.
//         while (secondJobDone.get() != 1) {

//         }
//         printThird.run();
//     }
// }

//使用信号量
// class Foo {
//     //Semaphore()括号中的是初始值
//     private Semaphore two = new Semaphore(-1);
//     private Semaphore three = new Semaphore(0);

//     public Foo() {
//     }

//     public void first(Runnable printFirst) throws InterruptedException {

//         // printFirst.run() outputs "first". Do not change or remove this line.
//         printFirst.run();
//         two.release();
//         two.release();
//     }

//     public void second(Runnable printSecond) throws InterruptedException {
//         two.acquire();
//         // printSecond.run() outputs "second". Do not change or remove this line.
//         printSecond.run();
//         three.release();
//     }

//     public void third(Runnable printThird) throws InterruptedException {

//         // printThird.run() outputs "third". Do not change or remove this line.
//         three.acquire();
//         printThird.run();
//     }
// }

//使用synchronized
// class Foo {

//     private boolean firstFinished;
//     private boolean secondFinished;
//     private Object lock = new Object();

//     public Foo() {
//     }

//     public void first(Runnable printFirst) throws InterruptedException {

//         // printFirst.run() outputs "first". Do not change or remove this line.
//         synchronized(lock) {
//             printFirst.run();
//             firstFinished = true;
//             lock.notifyAll();
//         }
//     }

//     public void second(Runnable printSecond) throws InterruptedException {
//         synchronized(lock) {
//             while (!firstFinished) {
//                 lock.wait();
//             }
//             printSecond.run();
//             secondFinished = true;
//             lock.notifyAll();
//         }
//     }

//     public void third(Runnable printThird) throws InterruptedException {

//         synchronized(lock) {
//             while (!secondFinished) {
//                 lock.wait();
//             }
//             printThird.run();
//         }

//     }
// }

//使用volatile
class 按序打印 {

    public 按序打印() {
    }

    volatile int count = 1;

    public void first(Runnable printFirst) throws InterruptedException {

        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (count != 2) ;
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (count != 3) ;
        printThird.run();
    }
}