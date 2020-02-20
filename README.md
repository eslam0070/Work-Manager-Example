# Work-Manager-Example

## What is WorkManager?

WorkManager is one of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) and part of Android Jetpack,
a new and opinionated take on how to build modern Android applications.

WorkManager is an Android library that runs deferrable background work when the work’s constraints are satisfied.

WorkManager is intended for tasks that require a guarantee that the system will run them even if the app exits.

In other words, WorkManager provides a battery-friendly API that encapsulates years of evolution of 
Android’s background behavior restrictions. 
This is critical for Android applications that need to execute background tasks!

## When to use WorkManager

WorkManager handles background work that needs to run when various constraints are met, 
regardless of whether the application process is alive or not. 
Background work can be started when the app is in the background,
when the app is in the foreground, or when the app starts in the foreground but goes to the background.
Regardless of what the application is doing, background work should continue to execute,
or be restarted if Android kills its process.

A common confusion about WorkManager is that it’s for tasks that needs to be run in a 
“background” thread but don’t need to survive process death. This is not the case. There are other
solutions for this use case like Kotlin’s coroutines, ThreadPools, or libraries like RxJava. 
You can find more information about this use case in the [guide to background processing.](https://developer.android.com/guide/background/)

There are many different situations in which you need to run background work,
and therefore different solutions for running background work. 
This blog post about background processing provides a lot of great information about when to use WorkManager.
Take a look at this diagram from the blog:

![Image description](https://miro.medium.com/max/960/1*K-jWMXQbAK98EdkuuaZCFg.png)

## Why use WorkManager?

WorkManager runs background work while taking care of compatibility issues and best practices
for battery and system health for you.
Furthermore, using WorkManager, you can schedule both periodic tasks and complex dependent 
chains of tasks: background work can be executed in parallel or sequentially,
where you can specify an execution order. WorkManager seamlessly handles passing along input and output between tasks.

You can also set criteria on when the background task should run. For example, 
there’s no reason to make an HTTP request to a remote server if the device doesn’t have a network connection. 
So you can set a Constraint that the task can only run when a network connection is present.

