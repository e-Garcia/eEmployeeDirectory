## App Description
Employee directory app that shows a list of employees from the provided endpoint

## Build tools & versions used

* **Android Studio:** Giraffe | 2022.3.1 Patch 2
* **Gradle:** 8.0
* **Android Gradle Plugin:** 8.0.2
* **Kotlin:** 1.8.21
* **Target SDK:** API 33
* **Minimum SDK:** API 21


## Steps to run the app

Using Android Studio (Giraffe | 2022.3.1 Patch 2 or later), run the application inside an Android
emulator or a physical Android device.

## What areas of the app did you focus on?

* **MVVM architecture:**
* Clear separation of responsibilities, follow SOLID principles.
* Make application code easy to understand and scalable
* Implement interfaces and use constructor based injection to make it more testable
* **Unit Testing:**
* Demonstrate how to unit test multiple layers 
* **Coding Standards**
* Followed: https://medium.com/@ajayjg/ids-layouts-resource-file-naming-android-naming-convention-3fc16e39721d

## What was the reason for your focus? What problems were you trying to solve?

* **Goal:** The primary goal was to build a functional Android app that demonstrates core Android
  development skills, including network requests, data persistence, and user authentication.

## How long did you spend on this project?
I spend about 9 hours during 3 days in about 3 hour blocks on this project as I hadn't created from 
scratch an application for a while. One big challenge was finding days of the week where I could 
spend long time blocks of uninterrupted, high focus time during non working hours.

Additionally, I went ahead and refined a few times my architecture and implementation details to reflect
my own coding standards, architecture and best practices instead of following any prior or current
company ones.

Finally, I am very detail oriented which aided me to review the requirements multiple times
and do my best to comply with each one, but it came at the cost of taking longer to complete 
the application.

## Did you make any trade-offs for this project? What would you have done differently with more time?

*   **Trade-offs:** 
* Due to time constraints, I focused on the architecture, coding, testing and best practices but 
* I spent minimal effort in the UI appearance, ADA compliance and behavior.
* 
*   **With More Time:**
* I would have created an intuitive, engaging UI using Material Design elements. 
* plus, worked on validating it for ADA compliance by checking color contrast, font size, 
* talkback navigation gestures and proper focus and label reading on view elements and more.

**Out of Scope**
  Tablet support
  Dark Mode
  Beautiful UI
  Compose


## What do you think is the weakest part of your project?

I think the weakest part of the project is the UI appearance and ADA compliance. With more time I would focused on it.

## Did you copy any code or dependencies? Please make sure to attribute them here!
No, I wrote everything from scratch.

## Is there any other information you’d like us to know?
I really enjoyed this project! Have a great day!



**Libraries**

## Core Android & UI:
AppCompat
Core KTX
Activity KTX
ConstraintLayout
CoordinatorLayout
RecyclerView
CardView
Lifecycle
ViewModel
LiveData
Paging
Material Components

## Networking:

Retrofit
Gson Converter

## Image Loading:

Glide

## Dependency Injection:

Hilt

## Testing:

JUnit
AndroidX Test (Espresso, Runner, Rules, Core)
Mockk
Robolectric