Notes CLI
Автор: axilgowo
GitHub: https://github.com/akhilgov11/notes-cli

Описание
notes-cli — консольное приложение на Java для управления текстовыми заметками. Заметки сохраняются в файл data/notes.csv, поэтому данные не теряются между запусками.

Команды
add — добавить заметку

--cmd=add --text="Текст заметки"
list — вывести все заметки

--cmd=list
rm — удалить заметку по ID

--cmd=rm --id=1
count — показать количество заметок

--cmd=count
Запуск локально
1. Компиляция
javac src/com/example/*.java

2. Использование

Добавить заметку:
java -cp src com.example.App --cmd=add --text="Купить хлеб"

Вывести список:
java -cp src com.example.App --cmd=list

Удалить заметку:
java -cp src com.example.App --cmd=rm --id=1

Посчитать заметки:
java -cp src com.example.App --cmd=count

Docker

Сборка образа
docker build -t notes-cli .

Запуск
Для сохранения данных создайте папку data:

mkdir -p data

Выполнение команд:

docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=add --text="Купить хлеб"
docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=list
docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=count

Версии (SemVer)
v1.0.0 — базовый функционал: add, list

v1.1.0 — новые команды: rm, count

Хранение данных

Все заметки сохраняются в файле:
data/notes.csv

Формат:
ID;Текст заметки;Дата создания

Пример работы
$ java -cp src com.example.App --cmd=add --text="Позвонить маме"
(ok)
$ java -cp src com.example.App --cmd=list
1;Позвонить маме
$ java -cp src com.example.App --cmd=count
1

Технологии:
Java 17

Docker

GitHub Actions (CI/CD)

Семантическое версионирование (SemVer)
