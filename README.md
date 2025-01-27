# Prueba tecnica para Mango por Juan Ángel
Este es mi resultado a la prueba tecnica para postularme a la vacante de desarrollador Android en Mango.

## Puntos Importantes

1. La prueba estaba en una version antigua y mi vacante requeria de usar jetpack compose, por lo que clone el repositorio original por completo y lo refactorize desde cero (Estaria encantado de reproducir el proceso antes un evaluador si estan interesados en ello).
   - Primero actualize la version AGP
   - Tras actualizar el AGP pude actualizar el Java al ultimo (21) esto no era del todo necesario, pero queria demostrar mis capacidades a la hora de refactorizar.
   - Despues elimine todas las dependencias que no eran necesarias y agregue las necesarias para jetpack compose.
   - Añadi las dependendicias necesarias para los tests y escribi algunos tests iniciales para empezar a programar.
2. Antes de acabar el tiempo cuando intente hacer un push de los cambios al repocitorio original, me di cuenta que el sistema de evaluacion no permite cambios en el gradle, por lo que me quede sin tiempo para subirlo, por lo que me he visto en la obligación a subirlo a un repositorio nuevo. Disculpad las molestias y espero que esto no sea un gran incoveniente.
3. Acontinuación dejo el README original de la prueba junto a un video Demonstrativo del funcionamiento, con la informacion necesaria para entender el problema y la solucion que he dado.
4. Gracias por la oportunidad y espero que mi solucion sea de su agrado.

---

### Introduction
This is a simple note-taking app for creating, editing and saving notes. You are expected to complete the application using `MainActivity`  and `ChangeNoteActivity` with `RecyclerView`.

### Problem Statement
Implement or rewrite the following functionalities:
* Each item shows a `note title` in `RecyclerView`.
* Recently saved notes are added to the end of `RecyclerView`.
* `welcome_fragment` with `welcome_message` and `CreateNoteButton()` are displayed if there aren’t any other notes visible.
* Clicking on the `list item`  triggers  `ChangeNoteActivity` with data from the selected item.
* `CreateNoteButton` adds a new note to the list and starts `ChangeNoteActivity` with the recently added item.
* The app returns `field_not_be_empty_error` if a field `Title` is empty.
* Clicking on the `SaveButton` saves the note, adds it to the list and returns to the `MainActivity`.

### Hints
* String constants should be taken in `string.xml`

### Note
Please be careful when editing `build.gradle` in your project. This task as it is doesn’t require any changes to it. It is generally ok to add new dependencies but changing or removing existing dependencies or configuration can cause the project and verification tests to not function in the expected way and give a unreliable score.

---

[demo.webm](https://github.com/user-attachments/assets/5a5e7322-5f69-4642-a4da-3e0260876828)
