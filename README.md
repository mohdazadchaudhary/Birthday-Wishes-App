## 1. Objective (Shared Understanding)

We are building a **single-purpose, emotionally driven Android app** using **Jetpack Compose + MVVM + Kotlin**. The app delivers a **6-page story-like birthday experience** with animations and background music. This document locks the **technical foundation** before implementation.

---

## 2. Architecture (Why & What)

### Chosen Architecture: **MVVM (Model–View–ViewModel)**

**Why MVVM fits perfectly here:**

* Clean separation of UI and logic
* Easy animation & state handling
* Interview-safe and production-grade
* Compose works best with unidirectional data flow

### Architecture Flow

```
UI (Compose Screens)
   ↓ observes
ViewModel (StateFlow)
   ↓ gets data from
Repository (Static content)
```

> No database, no network. Repository exists for **structure & scalability**, not complexity.

---

## 3. Modules & Project Structure

### Single-Module App (Recommended)

We intentionally keep **one module** to avoid overengineering.

```
app/
 ├─ ui/
 │   ├─ pager/
 │   │   ├─ BirthdayPager.kt
 │   │   ├─ Page1Welcome.kt
 │   │   ├─ Page2Special.kt
 │   │   ├─ Page3Forgot.kt
 │   │   ├─ Page4Wait.kt
 │   │   ├─ Page5Reveal.kt
 │   │   └─ Page6Celebration.kt
 │   ├─ components/
 │   │   ├─ AnimatedText.kt
 │   │   └─ MusicToggle.kt
 │   └─ theme/
 │       ├─ Color.kt
 │       ├─ Type.kt
 │       └─ Theme.kt
 ├─ viewmodel/
 │   └─ BirthdayViewModel.kt
 ├─ data/
 │   ├─ model/
 │   │   └─ BirthdayPage.kt
 │   └─ repository/
 │       └─ BirthdayRepository.kt
```

---

## 4. Libraries (Minimal, Intentional)

### Core (Mandatory)

```gradle
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.foundation:foundation")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
```

Purpose:

* UI rendering
* Horizontal pager
* ViewModel integration

---

### Animations (Celebration Layer)

```gradle
implementation("com.airbnb.android:lottie-compose:6.4.0")
```

Used for:

* Confetti
* Balloons
* Soft celebratory motion (Page 6)

---

### Background Music (Native – No Library)

We **do NOT use third-party music libraries**.

We use:

* `android.media.MediaPlayer`

**Why:**

* Lightweight
* Offline
* Full control
* No dependency risk

Music file location:

```
res/raw/birthday_music.mp3
```

Controlled entirely from **ViewModel**.

---

## 5. State Management Strategy

### Single Source of Truth

In `BirthdayViewModel`:

* `currentPage : StateFlow<Int>`
* `isMusicPlaying : StateFlow<Boolean>`

UI only **observes state**, never owns logic.

---

## 6. Navigation Strategy (Important Decision)

### ❌ No Navigation Component

### ✅ HorizontalPager Only

**Why:**

* This is a story, not an app with screens
* Pager feels natural, emotional, fluid
* Less boilerplate, more focus

Pager auto-scroll + swipe enabled.

---

## 7. Background Music Strategy

* Music starts on **Page 6 only**
* Toggle button (Play / Pause)
* Stops when app goes background

Handled via:

* ViewModel
* Lifecycle awareness

---

## 8. Design Philosophy (Guiding Principle)

* Emotion > Features
* Motion with restraint
* One idea, executed perfectly

> This app is a **digital letter**, not a utility.

---

## 9. What We Will Build Next (Execution Order)

1. Pager scaffold
2. Page 1 & Page 2 UI
3. ViewModel state wiring
4. Animations
5. Music integration
6. Final polish

---

**This document is our technical contract.**
We do not code until this is clear.
