package max.lab6.server.FileWorks;
import max.lab6.server.Task;
import max.lab6.server.collection.filecollection.CollectionReader;
import max.lab6.server.collection.filecollection.CollectionWriter;

import java.util.HashSet;

/**
 * Класс-менеджер коллекции
 */
public class CollectionManager {
    /**
     * Читатель коллекции
     */
    private CollectionReader reader;
    /**
     * Записыватель коллекции
     */
    private CollectionWriter writer;
    /**
     * Коллекция
     */
    private HashSet<Task> collection;

    /**
     * Конструктор
     * @param filePath Путь к файлу
     */
    public CollectionManager(String filePath){
        reader = new CollectionReader(filePath);
        writer = new CollectionWriter(filePath);
        collection = new HashSet<>();
    }

    public CollectionManager() {
    }

    /**
     * Читает из коллекции
     * @return Успешно ли прошла операция?
     */
    public boolean read(){
        if(reader.read()){
            collection = (HashSet<Task>) reader.getCollection();
            return  true;
        }
        return false;
    }

    /**
     * Записывает коллекцию
     * @return Успешно ли прошла операция?
     */
    public boolean write(){
        return writer.write(collection);
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
        return collection;
    }

    public void updateCollection() {
    }
}
