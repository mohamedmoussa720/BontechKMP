# لماذا نستخدم binaryTarget وليس .target مع Swift files؟

## المشكلة التي واجهتها

عند محاولة استخدام `.target` مع مجلد `Sources/BontechKMP`، حصلت على الخطأ:
```
unexpectedly did not find the new dependency in the package graph
```

## السبب

مشروع BontechKMP هو **Kotlin Multiplatform (KMP)** وليس Swift Package نقي:

### ❌ لماذا `.target` لا يعمل مع KMP:

1. **KMP يولد Kotlin code** → يُبنى إلى Framework/XCFramework
2. **Swift Package Manager يتوقع Swift files** في `Sources/BontechKMP`
3. **لا يمكن لـ Swift code استيراد framework خارجي** بدون `binaryTarget`

### مقارنة بين النوعين:

| الميزة | `.target` (Swift) | `.binaryTarget` (Framework) |
|--------|------------------|---------------------------|
| **الاستخدام** | Swift Package نقي | Compiled Framework |
| **الملفات المطلوبة** | ملفات `.swift` | `.xcframework` |
| **بناء** | يُبنى من Source | مبني مسبقاً |
| **مع KMP** | ❌ لا يعمل | ✅ يعمل |
| **الحجم في Git** | صغير | كبير (10-15 MB) |

## الحل الصحيح لـ KMP

### ✅ استخدام `binaryTarget` مع XCFramework:

```swift
// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "BontechKMP",
    platforms: [
        .iOS(.v14),
        .macOS(.v11)
    ],
    products: [
        .library(
            name: "BontechKMP",
            targets: ["BontechKMP"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "BontechKMP",
            path: "BontechKMP.xcframework"  // ✅ المسار للـ framework المبني
        )
    ]
)
```

## هل يمكن استخدام `.target` مع KMP؟

نعم، لكن بشروط:

### الخيار 1: SKIE مع Swift Exports (معقد)
- يتطلب إعداد SKIE خاص
- يولد Swift wrappers
- يحتاج integration معقد

### الخيار 2: Swift Wrappers يدوية (لا يُنصح به)
- إنشاء Swift files تستورد framework
- مشاكل في linking
- صعوبة في الصيانة

### الخيار 3: binaryTarget (الموصى به) ✅
- **بسيط ومباشر**
- **يعمل مباشرة من GitHub**
- **لا يحتاج إعداد معقد**

## النتيجة

لمشاريع **Kotlin Multiplatform** مثل BontechKMP:
- ✅ استخدم `binaryTarget` مع XCFramework
- ✅ ارفع XCFramework إلى repository (أو GitHub Releases)
- ✅ SwiftPM سيستورده تلقائياً

هذا هو الـ **standard approach** لتوزيع KMP libraries عبر Swift Package Manager.

## المراجع

- [Kotlin Multiplatform for iOS](https://kotlinlang.org/docs/multiplatform-mobile-create-first-app.html)
- [Swift Package Manager - Binary Targets](https://developer.apple.com/documentation/xcode/distributing-binary-frameworks-as-swift-packages)
- [SKIE Documentation](https://skie.touchlab.co/)

---

**الخلاصة**: `binaryTarget` مع XCFramework هو الحل الصحيح والموصى به لـ KMP! 🎉

