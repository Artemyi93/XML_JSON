# language: ru
@getResult
Функция: Получение выходного json-файла
    Предыстория:
        Пусть на вход программы подан xml-файл с одним корневым элементом only_root.xml
    Сценарий: Получение выходного json с 1 элементом
        Тогда на выходе получаем json с 1 элементом