# دليل دمج BontechKMP في مشاريع iOS

## نظرة عامة

تم حل مشكلة استيراد BontechKMP من GitHub! الآن يمكنك استيراد المكتبة بسهولة في أي مشروع iOS.

## ما تم تطبيقه

### 1. إضافة SKIE Plugin
تم إضافة [SKIE](https://skie.touchlab.co/) لتحسين التوافق مع Swift:
- يولد Swift interfaces أفضل
- يدعم async/await و Combine
- يحسن التعامل مع الأنواع (Types)

### 2. بناء XCFramework
تم إنشاء task جديد `assembleXCFramework` الذي:
- يبني frameworks لجميع معماريات iOS (arm64 للأجهزة، x64 و arm64 للـ simulator)
- يدمج simulator frameworks في fat binary واحد
- ينشئ XCFramework شامل

### 3. تحديث Package.swift
تم تحديث `Package.swift` لاستخدام `binaryTarget` مع المسار الصحيح للـ XCFramework.

### 4. إضافة XCFramework إلى Git
تم إزالة `*.xcframework` من `.gitignore` لإضافة الـ XCFramework المبني إلى repository.

## كيفية استخدام المكتبة في مشروع iOS

### الطريقة 1: استيراد من GitHub (موصى بها)

1. افتح مشروعك في Xcode
2. اذهب إلى **File → Add Packages...**
3. أدخل رابط repository:
   ```
   https://github.com/mohamedmoussa720/BontechKMP.git
   ```
4. اختر الفرع `main` أو أي tag معين
5. انقر على **Add Package**

### الطريقة 2: استيراد محلي

إذا كنت تطور المكتبة محلياً:

1. افتح مشروعك في Xcode
2. اذهب إلى **File → Add Packages...**
3. انقر على **Add Local...**
4. اختر مجلد `BontechKMP` من جهازك

## استخدام المكتبة في الكود

```swift
import BontechKMP

// مثال على استخدام Platform
let platform = getPlatform()
print(platform.name)  // يطبع: iOS

// مثال على استخدام Greeting
let greeting = Greeting()
print(greeting.greet())  // يطبع: Hello, iOS!
```

## بناء XCFramework محلياً

إذا أردت إعادة بناء الـ XCFramework:

```bash
./build-xcframework.sh
```

أو باستخدام Gradle مباشرة:

```bash
./gradlew :shared:assembleXCFramework
```

## الملفات المهمة

- `Package.swift` - تعريف Swift Package
- `BontechKMP.xcframework/` - الـ binary المبني للتوزيع
- `build-xcframework.sh` - سكريبت بناء XCFramework
- `shared/build.gradle.kts` - إعدادات Kotlin Multiplatform

## الفرق بين هذا الحل والحلول الأخرى

### ❌ المشكلة السابقة
```
local binary target 'BontechKMP' does not contain a binary artifact
```

**السبب**: الـ XCFramework لم يكن موجوداً في repository.

### ✅ الحل الحالي

**استخدام binaryTarget مع XCFramework مبني ومرفوع:**
- ✅ يعمل مباشرة عند الاستيراد من GitHub
- ✅ لا يحتاج بناء يدوي من المستخدم
- ✅ متوافق مع جميع معماريات iOS
- ✅ يستخدم SKIE لتحسين Swift APIs

## ملاحظات مهمة

### حجم Repository
- XCFramework يضيف ~8-10 MB لحجم repository
- إذا كنت تريد تقليل الحجم، يمكنك استخدام [GitHub Releases](#استخدام-github-releases-اختياري)

### تحديث المكتبة
عند إجراء تغييرات على الكود Kotlin:

1. قم ببناء XCFramework جديد:
   ```bash
   ./build-xcframework.sh
   ```

2. قم بـ commit و push التغييرات:
   ```bash
   git add BontechKMP.xcframework/
   git commit -m "Update XCFramework with new changes"
   git push
   ```

## استخدام GitHub Releases (اختياري)

لتقليل حجم repository، يمكنك استخدام GitHub Releases:

### 1. بناء XCFramework
```bash
./build-xcframework.sh
```

### 2. ضغط XCFramework
```bash
zip -r BontechKMP.xcframework.zip BontechKMP.xcframework
```

### 3. إنشاء Release على GitHub
1. اذهب إلى repository على GitHub
2. انقر على **Releases → Create a new release**
3. أنشئ tag جديد (مثلاً: `1.0.0`)
4. ارفع `BontechKMP.xcframework.zip` كـ asset
5. انشر الـ release

### 4. تحديث Package.swift
```swift
.binaryTarget(
    name: "BontechKMP",
    url: "https://github.com/mohamedmoussa720/BontechKMP/releases/download/1.0.0/BontechKMP.xcframework.zip",
    checksum: "SHA256_CHECKSUM_HERE"
)
```

لحساب الـ checksum:
```bash
swift package compute-checksum BontechKMP.xcframework.zip
```

## الدعم والمساعدة

إذا واجهت أي مشاكل:
1. تأكد من أنك تستخدم Xcode 14 أو أحدث
2. تأكد من أن iOS Deployment Target هو 14.0 أو أعلى
3. قم بتنظيف build folder في Xcode: **Product → Clean Build Folder**

## الخلاصة

الآن مشروع BontechKMP جاهز للاستخدام من GitHub مباشرة! 🎉

- ✅ SKIE مفعّل لتحسين Swift APIs
- ✅ XCFramework مبني ومضمّن
- ✅ Package.swift محدّث بشكل صحيح
- ✅ جاهز للاستيراد في أي مشروع iOS

