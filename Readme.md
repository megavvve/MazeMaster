# Библиотека MazeMaster

[![Сборка](https://img.shields.io/badge/Сборка-Gradle-green.svg)](https://gradle.org)

## Введение

MazeMaster — это Java-библиотека для генерации и решения лабиринтов для консольных игр. Она предоставляет:
- **Генерация лабиринтов**: Алгоритмы Прима и Краскала.
- **Решение лабиринтов**: Алгоритмы поиска в ширину (BFS) и A*.
- **Отображение в консоли**: Визуализация с использованием ANSI-цветов для стен, путей и координат.

Библиотека идеально подходит для разработчиков, создающих консольные игры или приложения, связанные с лабиринтами.

## Требования

- **Java**: Версия 17 (указана в `build.gradle`).
- **Gradle**: Используйте включённый Gradle Wrapper (`gradlew`/`gradlew.bat`). Глобальная установка Gradle не требуется.

## Сборка библиотеки

Библиотека собирается с использованием Gradle Wrapper, что исключает необходимость глобальной установки Gradle.

### Шаги для сборки
1. Склонируйте репозиторий или перейдите в директорию проекта.
2. Выполните команду:
   ```
   ./gradlew build
   ```
   На Windows:
   ```
   .\gradlew.bat build
   ```
3. JAR-файл будет создан в директории `build/libs`, обычно с именем `com.mazemaster-1.0-SNAPSHOT.jar`.

### Генерация Javadoc
Для создания документации API:
1. Выполните:
   ```
   ./gradlew javadoc
   ```
   На Windows:
   ```
   .\gradlew.bat javadoc
   ```
2. Документация будет создана в `build/docs/javadoc`. Откройте `index.html` в браузере для просмотра.

## Использование библиотеки

Добавьте JAR-файл в classpath вашего проекта. Для проектов на Gradle добавьте в `build.gradle`:
```groovy
dependencies {
    implementation files('путь/к/com.mazemaster-1.0-SNAPSHOT.jar')
}
```
Замените `'путь/к/com.mazemaster-1.0-SNAPSHOT.jar'` на актуальный путь к JAR.

### Пример использования
1. **Генерация лабиринта**:
   ```java
   import com.mazemaster.services.generator.Generator;
   import com.mazemaster.services.generator.PrimMazeGenerator;
   import com.mazemaster.model.Maze;

   Generator generator = new PrimMazeGenerator();
   Maze maze = generator.generate(10, 10); // Лабиринт 10x10
   ```

2. **Отображение лабиринта**:
   ```java
   import com.mazemaster.view.MazeRenderer;

   MazeRenderer renderer = new MazeRenderer();
   System.out.println(renderer.render(maze));
   ```

3. **Решение лабиринта**:
   ```java
   import com.mazemaster.services.solver.Solver;
   import com.mazemaster.services.solver.AStarSolver;
   import com.mazemaster.model.Coordinate;
   import com.mazemaster.model.Cell;
   import java.util.List;

   Solver solver = new AStarSolver();
   Coordinate start = new Coordinate(0, 0);
   Coordinate end = new Coordinate(9, 9);
   List<Cell> path = solver.solve(maze, start, end);
   ```

4. **Отображение с путём**:
   ```java
   if (path != null && !path.isEmpty()) {
       System.out.println(renderer.render(maze, path));
   } else {
       System.out.println("Путь не найден!");
   }
   ```

## Структура проекта

- **`com.mazemaster.model`**:
    - Классы для структуры лабиринта: `Maze`, `Cell`, `Coordinate`.
- **`com.mazemaster.view`**:
    - Классы для отображения в консоли: `Renderer`, `MazeRenderer`.
- **`com.mazemaster.services.generator`**:
    - Алгоритмы генерации лабиринтов: `Generator`, `PrimMazeGenerator`, `KruskalMazeGenerator`.
- **`com.mazemaster.services.solver`**:
    - Алгоритмы решения лабиринтов: `Solver`, `AStarSolver`, `BFSSolver`, `PathReconstruct`, `MazeCellNeighborFinder`.
- **`com.mazemaster.utils`**:
    - Утилитные классы: `MazeUtils`, `ConsoleColors`.
- **`com.mazemaster.example`**:
    - Пример кода: `MazeExample`.

## Запуск примера
Класс `MazeExample` демонстрирует функциональность библиотеки:
1. Добавьте в `build.gradle`, чтобы сделать JAR исполняемым:
   ```groovy
   jar {
       manifest {
           attributes 'Main-Class': 'com.mazemaster.example.MazeExample'
       }
   }
   ```
2. Пересоберите:
   ```
   .\gradlew.bat build
   ```
3. Запустите:
   ```
   java -jar build/libs/com.mazemaster-1.0-SNAPSHOT.jar
   ```
4. Следуйте подсказкам в консоли для генерации и решения лабиринта.




## Устранение неполадок

- **Ошибка: `gradle: команда не найдена`**:
    - Используйте Gradle Wrapper: `.\gradlew.bat` вместо `gradle`.
    - Если wrapper отсутствует, создайте его (требуется глобальный Gradle):
      ```
      gradle wrapper
      ```
- **Ошибка сборки: Проблема компиляции**:
    - Убедитесь, что установлена Java 17 (`java -version`).
    - Проверьте синтаксические ошибки в исходных файлах, особенно в `Coordinate.java`.
    - Выполните `.\gradlew.bat clean build` для очистки старых артефактов.
- **Javadoc не генерируется**:
    - Убедитесь, что исходные файлы синтаксически корректны.
    - Проверьте результат в `build/docs/javadoc`.