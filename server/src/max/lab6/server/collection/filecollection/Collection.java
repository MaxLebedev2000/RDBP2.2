package max.lab6.server.collection.filecollection;


import max.lab6.server.Task;
import max.lab6.server.collection.Reader;
import max.lab6.server.collection.Writer;
import max.lab6.server.collection.databasecollection.CollectionManager;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс-менеджер коллекции
 */
public class Collection implements CollectionManager {
    /**
     * Читатель коллекции
     */
    private Reader reader;
    /**
     * Записыватель коллекции
     */
    private Writer writer;
    /**
     * Коллекция
     */
    private Set<Task> collection;

    /**
     * Конструктор
     * @param filePath Путь к файлу
     */
    public Collection(String filePath){
        reader = new CollectionReader(filePath);
        writer = new CollectionWriter(filePath);
        collection = ConcurrentHashMap.newKeySet();
    }

    /**
     * Читает из коллекции
     * @return Успешно ли прошла операция?
     */
    public boolean read(){
        if(reader.read()){
            collection = reader.getCollection();
            return  true;
        }
        return false;
    }

    /**
     * Обновляет коллекцию из файла
     */
    public void updateCollection(){
        collection.clear();
        read();
    }

    /**
     * Записывает коллекцию
     * @return Успешно ли прошла операция?
     */
    public boolean write(){
        boolean result = writer.write(collection);
        writer.reset();
        return result;
    }

    /**
     * Завешает работу менеджера коллекцией
     */
    public void close(){
        writer.close();
    }

    /**
     * Геттер коллекции
     * @return Коллекция из Card
     */
    public HashSet<Task> getCollection() {
        return (HashSet<Task>) collection;
    }
}
