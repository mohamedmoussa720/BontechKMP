# 📦 تعليمات إنشاء Release

## المشكلة التي تم حلها

المشكلة كانت أن **XCFramework موجود في Git LFS**، و SwiftPM لا يدعم Git LFS تلقائياً.

**الحل**: استخدام **GitHub Releases** مع zip file و checksum.

---

## 🚀 خطوات إنشاء Release (للمرة الأولى)

### 1️⃣ بناء XCFramework وضغطه

```bash
./create-release.sh 1.0.0 "Initial release with XCFramework"
```

هذا سيقوم بـ:
- ✅ بناء XCFramework
- ✅ ضغطه إلى zip
- ✅ حساب checksum
- ✅ إعطائك التعليمات

### 2️⃣ إنشاء Git Tag

```bash
git tag 1.0.0
git push origin 1.0.0
```

### 3️⃣ إنشاء Release على GitHub

1. اذهب إلى: https://github.com/mohamedmoussa720/BontechKMP/releases/new
2. اختر **Tag**: `1.0.0`
3. **Title**: `Release 1.0.0`
4. **Description**: 
   ```
   Initial release with XCFramework
   
   - Built with SKIE for better Swift interop
   - Supports iOS 14+ and macOS 11+
   - Includes arm64 (device) and simulator architectures
   ```
5. **Upload**: اسحب `BontechKMP.xcframework.zip` إلى منطقة الرفع
6. انقر **Publish release**

### 4️⃣ تحديث Package.swift

`Package.swift` محدث بالفعل مع:
- ✅ URL: `https://github.com/mohamedmoussa720/BontechKMP/releases/download/1.0.0/BontechKMP.xcframework.zip`
- ✅ Checksum: `6b50b227e558c090f40da385626221925c85e33a5daffd479093c62e16504dbc`

### 5️⃣ Commit و Push

```bash
git add Package.swift .gitignore create-release.sh RELEASE_INSTRUCTIONS.md
git commit -m "Fix: Use GitHub Releases instead of Git LFS

- Changed Package.swift to use binaryTarget with URL and checksum
- Added create-release.sh script for easy release creation
- Updated .gitignore to exclude xcframework files
- Fixes: SwiftPM cannot access Git LFS files

This resolves: 'unexpectedly did not find the new dependency' error"

git push origin main
```

---

## 🔄 إنشاء Release جديد (للتحديثات المستقبلية)

عند إجراء تغييرات على الكود:

```bash
# 1. بناء XCFramework جديد
./create-release.sh 1.0.1 "Bug fixes and improvements"

# 2. تحديث Package.swift بالـ checksum الجديد

# 3. إنشاء tag و release
git tag 1.0.1
git push origin 1.0.1

# 4. رفع zip على GitHub Releases
```

---

## ✅ التحقق من أن كل شيء يعمل

بعد إنشاء Release:

### في Xcode:

1. **File → Add Packages...**
2. **URL**: `https://github.com/mohamedmoussa720/BontechKMP.git`
3. **Version**: `1.0.0` أو `main`
4. **Add Package** ✅

### في الكود:

```swift
import BontechKMP

let platform = getPlatform()
print(platform.name)  // "iOS"
```

---

## 📊 الفرق بين الحلول

| الحل | المميزات | العيوب |
|------|---------|--------|
| **Git LFS** | ❌ SwiftPM لا يدعمه | ❌ يحتاج إعداد خاص |
| **رفع مباشر** | ✅ بسيط | ❌ يزيد حجم repo |
| **GitHub Releases** ✅ | ✅ يعمل مع SwiftPM | ✅ لا يزيد حجم repo | ✅ موصى به من Apple |

---

## 🎯 الخلاصة

**المشكلة**: Git LFS لا يعمل مع SwiftPM  
**الحل**: GitHub Releases مع zip + checksum  
**النتيجة**: ✅ يعمل مباشرة من GitHub!

---

## 📝 ملاحظات

- **Checksum**: يجب أن يتطابق مع zip file المرفوع
- **Version**: يجب أن يتطابق مع tag في GitHub
- **URL**: يجب أن يكون صحيحاً ويشير إلى release موجود

إذا واجهت أي مشاكل، تأكد من:
1. ✅ Release موجود على GitHub
2. ✅ Zip file مرفوع بشكل صحيح
3. ✅ Checksum في Package.swift صحيح
4. ✅ Tag موجود ويشير إلى commit صحيح

