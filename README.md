![Travis-ci](https://api.travis-ci.org/MLSDev/AnimatedRecyclerView.svg)
[![License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)
[![Download](https://api.bintray.com/packages/spetrosiukmlsdev/AnimatedRecyclerView/AnimatedRecyclerView/images/download.svg)](https://bintray.com/spetrosiukmlsdev/AnimatedRecyclerView/AnimatedRecyclerView/_latestVersion)

# AnimatedRecyclerView
A RecyclerView with layout animations

## Demo
<img src="art/demo.gif" width="32%">

## Setup
To use this library your `minSdkVersion` must be >= 16.

In your build.gradle :
```gradle
dependencies {
    implementation "com.mlsdev.animatedrv:library:1.0.1"
}
```

## Example
### From the layout
```xml
<com.mlsdev.animatedrv.AnimatedRecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:animationDuration="600"
        app:layoutAnimation="@anim/layout_animation_from_bottom"
        app:layoutManagerOrientation="vertical"
        app:layoutManagerReverse="false"
        app:layoutManagerType="linear" />
```
#### You can create any layout animations
```xml
<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
    android:animation="@anim/item_animation_from_bottom"
    android:animationOrder="normal"
    android:delay="15%" />
```
```xml
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="500">

    <translate
        android:fromYDelta="50%p"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:toYDelta="0" />

    <alpha
        android:fromAlpha="0"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:toAlpha="1" />

</set>
```

### From the code
```java
AnimatedRecyclerView recyclerView = new AnimatedRecyclerView.Builder(this)
                .orientation(LinearLayoutManager.VERTICAL)
                .layoutManagerType(AnimatedRecyclerView.LayoutManagerType.LINEAR)
                .animation(R.anim.layout_animation_from_bottom)
                .animationDuration(600)
                .reverse(false)
                .build();
```

### Start animation
- First option
```java
adapter.notifyDataSetChanged();
recyclerView.scheduleLayoutAnimation();
```
- Second option
Your `RecyclerView` must be casted to the `AnimatedRecyclerView` and an adapter must be set.
```java
recyclerView.notifyDataSetChanged();
```

## Authors
* [Sergey Petrosyuk](mailto:petrosyuk@mlsdev.com), MLSDev 

## About MLSDev

[<img src="https://cloud.githubusercontent.com/assets/1778155/11761239/ccfddf60-a0c2-11e5-8f2a-8573029ab09d.png" alt="MLSDev.com">][mlsdev]

AnimatedRecyclerView is maintained by MLSDev, Inc. We specialize in providing all-in-one solution in mobile and web development. Our team follows Lean principles and works according to agile methodologies to deliver the best results reducing the budget for development and its timeline. 

Find out more [here][mlsdev] and don't hesitate to [contact us][contact]!

[mlsdev]: http://mlsdev.com
[contact]: http://mlsdev.com/contact_us
[github-frederikos]: https://github.com/SerhiyPetrosyuk
