# Coilifier-Android

[![Stars](https://img.shields.io/github/stars/DATL4G/Coilifier-Android.svg)](https://github.com/DATL4G/Coilifier-Android)

[![Forks](https://img.shields.io/github/forks/DATL4G/Coilifier-Android.svg)](https://github.com/DATL4G/Coilifier-Android)

[![License](https://img.shields.io/github/license/DATL4G/Coilifier-Android.svg)](https://github.com/DATL4G/Coilifier-Android)

Coilifier-Android is an open source extension library for Android to use Glide in a more Coil way
* ImageView extension functions
* Target extension functions
* provides Kotlin Builder DSL

## Setup
Add this line in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
        ...
    }
}
```
Add this line in your app build.gradle:
```gradle

dependencies {
    ...
    implementation("com.github.DatL4g:Coilifier-Android:1.0.0")
    ...
}
```

## Goal
The goal is to bring Glide closer to the style of Coil.
Glide usually loads faster than Coil (which I especially notice in RecyclerViews) but the Extension Function in Coil is much more innovative.
And to get the best out of both, I created this library

## Load Images into Target or ImageView
Multiple ResourceType implementations in load functions is provided

### Generic ResourceType
| Glide                                                                | Coilifier                      |
|:-------------------------------------------------------------------- |:------------------------------ |
|`Glide.with(this).as(Drawable::class.java).load(any).into(target)`    |`target.load<Drawable>(any)`    |
|`Glide.with(this).as(Bitmap::class.java).load(any).into(target)`      |`target.load<Bitmap>(any)`      |
|`Glide.with(this).as(File::class.java).load(any).into(target)`        |`target.load<File>(any)`        |
|`Glide.with(this).as(GifDrawable::class.java).load(any).into(target)` |`target.load<GifDrawable>(any)` |
|`Glide.with(this).as(Custom::class.java).load(any).into(target)`      |`target.load<Custom>(any)`      |

### Fixed ResourceType
| Glide                                                 | Coilifier                 |
|:----------------------------------------------------- |:------------------------- |
|`Glide.with(this).asDrawable().load(any).into(target)` |`target.loadDrawable(any)` |
|`Glide.with(this).asBitmap().load(any).into(target)`   |`target.loadBitmap(any)`   |
|`Glide.with(this).asFile().load(any).into(target)`     |`target.loadFile(any)`     |
|`Glide.with(this).asGif().load(any).into(target)`      |`target.loadGif(any)`      |

### Builder
Available functions
* `error(resId: Int)`
* `error(drawable: Drawable)`
* `error(bitmap: Bitmap)`
* `error(view: View)` // Captures View or gets current image of ImageView
* `placeholder(resId: Int)`
* `placeholder(drawable: Drawable)`
* `placeholder(bitmap: Bitmap)`
* `placeholder(view: View)` // Captures View or gets current image of ImageView
* `thumbnail(sizeMultiplier: Float)`
* `thumbnail(vararg requestBuilder: RequestBuilder<ResourceType>)`
* `transition(transitionOptions: TransitionOptions<*, in ResourceType?>)`
* `transform(vararg transformations: Transformation<Bitmap?>)`
* `scaleType(scale: Scale)`
* `fitCenter()`
* `centerCrop()`
* `centerInside()`
* `circleCrop()`
* `optionalFitCenter()`
* `optionalCenterCrop()`
* `optionalCenterInside()`
* `optionalCircleCrop()`
* `apply(requestOptions: BaseRequestOptions<*>)`

#### Usage
```kotlin
target.load<Drawable>(any) {
    fitCenter()
    transform(RoundedCorners(25))
}

// equals

Glide.with(this).asDrawable().load(any).fitCenter().transform(RoundedCorners(25)).into(target)
```

## Maintainers
This project is mantained by:
| Avatar | Contributor |
|---|:---:|
| [![](https://avatars3.githubusercontent.com/u/46448715?s=50&v=4)](http://github.com/DatL4g) | [DatLag](http://github.com/DatL4g) |

## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request

## License

View full license [here](LICENSE). In short:

> A permissive license whose main conditions require preservation of copyright and license notices. Contributors provide an express grant of patent rights. Licensed works, modifications, and larger works may be distributed under different terms and without source code.
