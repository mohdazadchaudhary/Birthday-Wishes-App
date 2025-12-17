# 🎂 Birthday Wishes App

A **single‑purpose, emotionally driven Android application** built with **Jetpack Compose + MVVM + Kotlin**. The app presents a **story‑like birthday experience** using full‑screen images, smooth paging, and background music — designed as a *digital letter*, not a utility.

---

## ✨ Vision

> Emotion over features. Flow over noise.

This app is crafted to deliver a calm, cinematic birthday journey:

* One screen at a time
* Full‑screen visuals
* Gentle transitions
* Music that supports the moment

No ads. No login. No distractions.

---

## 🧠 Architecture

**Pattern:** MVVM (Model–View–ViewModel)

**Why MVVM?**

* Clean separation of UI and logic
* Predictable state handling with Compose
* Easy to scale without overengineering
* Interview‑safe and production‑ready

**Data Flow**

```
UI (Compose)
  ↓ observes
ViewModel (StateFlow)
  ↓ provides
Repository (static content)
```

> No database, no network. Repository exists for structure and clarity.

---

## 🗂️ Current File Structure

```
com.example.birthdaywishesapp
│
├── data/
│   └── BirthdayRepository.kt
│
├── model/
│   └── BirthdayPage.kt
│
├── ui/
│   ├── pager/
│   │   └── BirthdayPager.kt
│   │
│   ├── pages/
│   │   └── ImagePage.kt
│   │
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Type.kt
│   │   └── Theme.kt
│   │
│   └── util/
│       └── MusicPlayer.kt
│
├── viewmodel/
│   └── BirthdayViewModel.kt
│
└── MainActivity.kt

res/
├── drawable/
│   ├── page1.png
│   ├── page2.png
│   ├── page3.png
│   ├── page4.png
│   └── page5.png
│
└── raw/
    ├── bg_flow_music.mp3
    └── birthday_celebration.mp3
```

---

## 📱 UI & Navigation

* **Navigation:** `HorizontalPager` (no Navigation Component)
* **Reason:** This is a story, not a multi‑screen app
* **Interaction:**

    * Auto‑slide between pages
    * Manual swipe supported
    * Full‑screen image per page

Each page represents a *moment* in the birthday journey.

---

## 🎵 Music Strategy

* Background music handled via **MediaPlayer**
* No third‑party audio libraries
* Music logic centralized in `MusicPlayer`
* Controlled by `BirthdayViewModel`

**Audio Files**

* `bg_flow_music.mp3` → calm background flow
* `birthday_celebration.mp3` → final celebration

---

## 🔁 State Management

The app uses **StateFlow** for predictable UI updates:

* `pages : StateFlow<List<BirthdayPage>>`
* `currentPage : StateFlow<Int>`
* `musicType : StateFlow<MusicType>`

UI only **observes state** — it does not own business logic.

---

## 🛠️ Libraries Used

```gradle
androidx.compose.material3
androidx.compose.foundation
androidx.lifecycle.viewmodel-compose
```

Minimal by design. Every dependency has a purpose.

---

## 🚧 Current Status

✅ Project structure finalized
✅ MVVM wired
✅ Pager working
✅ Images loading full‑screen
✅ Music integration implemented

⏸️ Animations intentionally paused for refinement

This repository reflects **work completed up to this stage**.

---

## 🌱 Next Possible Enhancements (Optional)

* Fade / cinematic image transitions
* Subtle text overlays
* Confetti or Lottie animation on final page
* Lifecycle‑aware music pause/resume

These are *enhancements*, not requirements.

---

## ❤️ Final Note

This app is not about complexity.
It’s about **care**.

A small idea, built thoughtfully.
