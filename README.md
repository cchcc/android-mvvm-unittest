# android-mvvm-unittest
To learn android [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) based on [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) and unit test.

- Start with basic and simple.
- Each example should has a unit test at least or more.


## E01 - Simple Activity

Simple [Data Binding](https://developer.android.com/topic/libraries/data-binding/),
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel),
[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) on an Activity.

Two-way binding at EditText.

Simple Unit Test with [Espresso](https://developer.android.com/training/testing/espresso/).


## E02 - Simple Fragment with some animations

Simple [Data Binding](https://developer.android.com/topic/libraries/data-binding/),
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel),
[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) on an Fragment.

Some animations with [lottie](http://airbnb.io/lottie/)(resources by [Eddy Gann](https://www.lottiefiles.com/ed117)).

Custom binding attribute(onAnimationEnd) using
[BindingAdapter](https://developer.android.com/topic/libraries/data-binding/binding-adapters#custom-logic).

For single Fragment android instrumented test,
there is a dummy activity which only works on debug and test that implemented with
[@RestrictTo](https://developer.android.com/reference/android/support/annotation/RestrictTo)
and debug [Source set](https://developer.android.com/studio/build/build-variants#sourcesets).

For unit test, it needs to decouple the logic that getting boolean result from ViewModel.
so [ViewModelProvider.Factory](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider.Factory) is here.