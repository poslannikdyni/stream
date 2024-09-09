package by.clevertec.util;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.reader.JsonReader;
import by.clevertec.util.reader.Reader;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class Util {

    private static final String ANIMALS_DATA_FILE = "src/main/resources/json/animals.json";
    private static final String RECRUITS_DATA_FILE = "src/main/resources/json/recruits.json";
    private static final String CARS_DATA_FILE = "src/main/resources/json/cars.json";
    private static final String FLOWERS_DATA_FILE = "src/main/resources/json/flowers.json";
    private static final String STUDENTS_DATA_FILE = "src/main/resources/json/students.json";
    private static final String EXAMINATION_DATA_FILE = "src/main/resources/json/examinations.json";
    private static final String BUILDING_TYPE_HOSPITAL = "Hospital";
    private static final String BUILDING_TYPE_OTHER = "Civil building";

    private static final Reader reader = new JsonReader();

    public static List<Animal> getAnimals() {
        return reader.getModelData(ANIMALS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Person> getPersons() {
        return reader.getModelData(RECRUITS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Car> getCars() {
        return reader.getModelData(CARS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Flower> getFlowers() {
        return reader.getModelData(FLOWERS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<House> getHouses() {
        List<Person> personList = getPersons();

        return List.of(
                new House(1, BUILDING_TYPE_HOSPITAL, personList.subList(0, 3)),
                new House(2, BUILDING_TYPE_OTHER, personList.subList(4, 7)),
                new House(3, BUILDING_TYPE_OTHER, personList.subList(8, 9)),
                new House(4, BUILDING_TYPE_OTHER, personList.subList(10, 13)),
                new House(5, BUILDING_TYPE_OTHER, personList.subList(14, 19)),
                new House(6, BUILDING_TYPE_OTHER, personList.subList(20, 20))
        );
    }

    public static List<Student> getStudents() {
        return reader.getModelData(STUDENTS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Examination> getExaminations() {
        return reader.getModelData(EXAMINATION_DATA_FILE, new TypeReference<>() {
        });
    }
}
