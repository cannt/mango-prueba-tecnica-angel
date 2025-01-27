## Introduction
This is a simple note-taking app for creating, editing and saving notes. You are expected to complete the application using `MainActivity`  and `ChangeNoteActivity` with `RecyclerView`.

## Problem Statement
Implement or rewrite the following functionalities:
* Each item shows a `note title` in `RecyclerView`.
* Recently saved notes are added to the end of `RecyclerView`.
* `welcome_fragment` with `welcome_message` and `CreateNoteButton()` are displayed if there aren’t any other notes visible.
* Clicking on the `list item`  triggers  `ChangeNoteActivity` with data from the selected item.
* `CreateNoteButton` adds a new note to the list and starts `ChangeNoteActivity` with the recently added item.
* The app returns `field_not_be_empty_error` if a field `Title` is empty.
* Clicking on the `SaveButton` saves the note, adds it to the list and returns to the `MainActivity`.

## Hints
* String constants should be taken in `string.xml`

## Note
Please be careful when editing `build.gradle` in your project. This task as it is doesn’t require any changes to it. It is generally ok to add new dependencies but changing or removing existing dependencies or configuration can cause the project and verification tests to not function in the expected way and give a unreliable score.
