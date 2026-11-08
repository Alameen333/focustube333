# FocusTube

A native Android app for an intentionally curated YouTube library. This repository was empty when work began; the initial commit establishes a Kotlin + Jetpack Compose Android application shell.

## Build

Open this project in Android Studio (JDK 17, Android SDK 35) and run the `app` configuration. The repository currently does not include a Gradle wrapper.

## Current milestone

- Native Android / Kotlin / Jetpack Compose project structure.
- Five-section bottom navigation shell with a dark charcoal and mint visual foundation.
- Honest empty states; no sample or fabricated video/channel content is shown.

This is a starting point, not a feature-complete product. Channel extraction, local persistence, real search, playback, backup, and the remaining product requirements are not implemented yet.

## Implementation plan

1. Architecture and design system: introduce app theme, navigation/state ownership, repositories, and testable boundaries.
2. Local data: Room entities/DAOs for channels, videos, playlists, playback progress and bookmarks; DataStore for preferences; migrations and backup validation.
3. Extraction: integrate NewPipe Extractor behind a cancellable repository interface; resolve channels/videos explicitly and paginate without duplicates.
4. Management: implement channel approval/removal, individual video approval, curated feeds, playlists, history and local search.
5. Playback: integrate Media3 with supported stream selection, progress persistence, resume/restart, subtitles and lifecycle-aware controls.
6. Focus and settings: persistent Focus Mode, theme preferences, optional viewing controls, privacy and data management.
7. Verification: unit/UI tests, build and device checks, network/error-state testing, and documented limitations.

No YouTube API key is used. Do not claim extraction or playback support until implemented and tested; respect platform restrictions and do not bypass DRM or access controls.
