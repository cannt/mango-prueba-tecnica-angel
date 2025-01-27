# Prueba técnica para Mango por Juan Ángel

Este es mi resultado a la prueba técnica para postularme a la vacante de desarrollador Android en Mango.

## Puntos Importantes

1. La prueba estaba en una versión antigua y mi vacante requería usar Jetpack Compose, por lo que cloné el repositorio original por completo y lo refactoricé desde cero (estaría encantado de reproducir el proceso ante un evaluador si están interesados en ello).
   - Primero actualicé la versión AGP.
   - Tras actualizar el AGP, pude actualizar Java al último (21). Esto no era del todo necesario, pero quería demostrar mis capacidades a la hora de refactorizar.
   - Después eliminé todas las dependencias que no eran necesarias y agregué las necesarias para Jetpack Compose.
   - Añadí las dependencias necesarias para los tests y escribí algunos tests iniciales para empezar a programar.
2. Antes de acabar el tiempo, cuando intenté hacer un push de los cambios al repositorio original, me di cuenta de que el sistema de evaluación no permite cambios en el Gradle, por lo que me quedé sin tiempo para subirlo. Por esto, me he visto en la obligación de subirlo a un repositorio nuevo. Disculpad las molestias y espero que esto no sea un gran inconveniente.
3. A continuación, dejo el README original de la prueba junto a un video demostrativo del funcionamiento, con la información necesaria para entender el problema y la solución que he dado.
4. Gracias por la oportunidad y espero que mi solución sea de su agrado.

---

### Introduction

This is a simple note-taking app for creating, editing, and saving notes. You are expected to complete the application using `MainActivity` and `ChangeNoteActivity` with `RecyclerView`.

### Problem Statement

Implement or rewrite the following functionalities:
* Each item shows a `note title` in `RecyclerView`.
* Recently saved notes are added to the end of `RecyclerView`.
* `welcome_fragment` with `welcome_message` and `CreateNoteButton()` are displayed if there aren’t any other notes visible.
* Clicking on the `list item` triggers `ChangeNoteActivity` with data from the selected item.
* `CreateNoteButton` adds a new note to the list and starts `ChangeNoteActivity` with the recently added item.
* The app returns `field_not_be_empty_error` if a field `Title` is empty.
* Clicking on the `SaveButton` saves the note, adds it to the list, and returns to the `MainActivity`.

### Hints

* String constants should be taken in `string.xml`.

### Note

Please be careful when editing `build.gradle` in your project. This task, as it is, doesn’t require any changes to it. It is generally ok to add new dependencies, but changing or removing existing dependencies or configuration can cause the project and verification tests to not function in the expected way and give an unreliable score.

---

[demo.webm](https://github.com/user-attachments/assets/5a5e7322-5f69-4642-a4da-3e0260876828)
