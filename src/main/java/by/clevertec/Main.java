package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;
import by.clevertec.util.data.NumberList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summarizingDouble;

public class Main {
    private static final String BUILDING_TYPE_HOSPITAL = "Hospital";
    private static final String BUILDING_TYPE_OTHER = "Civil building";

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();

        task13();
//        task14();
//        task15();

//        task16();
//        task17();
//        task18();
//        task19();
//        task20();
//        task21();
//        task22();
    }

    // ### Задача №1
    //     Из представленных животных отобрать все молодые особи от 10 до 20 лет и отсортировать по возрасту (по возрастанию)
    //     далее - распределить по 7 на каждый зоопарк. Зоопарков неограниченное кол-во а вы - директор 3-го по счёту зоопарка.
    //     Полученных животных вывести в консоль.
    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        var zoo = animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(() -> new NumberList<Animal>(1, 7),
                        NumberList::add,
                        NumberList::merge
                );

        zoo.getContent()
                .getOrDefault(3, new ArrayList<>())
                .forEach(System.out::println);

//        printAllZoo(zoo);
    }

    // Вспомогательный метод, для того что бы посмотреть как группируются животные в зоопарк по 7
    private static void printAllZoo(NumberList<Animal> zoo) {
        zoo.getContent()
                .forEach((zooName, animalsList) -> {
                            System.out.print(zooName);
                            System.out.println(animalsList);
                        }
                );
    }

    // ### Задача №2
    //     Отобрать всех животных из Японии (Japanese) и записать породу UPPER_CASE в если пол Female
    //     преобразовать к строкам породы животных и вывести в консоль
    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> "Japanese".equalsIgnoreCase(animal.getOrigin()) && animal.getBread() != null)
                .map(animal ->
                        "Female".equalsIgnoreCase(animal.getGender()) ?
                                animal.getBread().toUpperCase() :
                                animal.getBread())
                .distinct()
                .forEach(System.out::println);
    }

    // ### Задача №3
    //     Отобрать всех животных старше 30 лет и вывести все страны происхождения без дубликатов начинающиеся на "A"
    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(Objects::nonNull)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    // ### Задача №4
    //     Подсчитать количество всех животных пола = Female. Вывести в консоль
    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        var countFemaleAnimals = animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> "Female".equalsIgnoreCase(animal.getGender()))
                .count();

        System.out.println(countFemaleAnimals);
    }

    // ### Задача №5
    //     Взять всех животных возрастом 20 - 30 лет. Есть ли среди нах хоть один из страны Венгрия (Hungarian)?
    //     Ответ вывести в консоль
    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .filter(animal -> "Hungarian".equalsIgnoreCase(animal.getOrigin()))
                .findAny()
                .ifPresent(animal -> System.out.println("Find animal from Hungarian : " + animal));
    }

    // ### Задача №6
    //     Взять всех животных. Все ли они пола Male и Female ?
    //     Ответ вывести в консоль
    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        var genders = animals.stream()
                .filter(Objects::nonNull)
                .map(Animal::getGender)
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        System.out.println(genders.size() == 2 && genders.contains("male") && genders.contains("female"));
    }

    // ### Задача №7
    //     Взять всех животных. Узнать что ни одно из них не имеет страну происхождения Oceania.
    //     Ответ вывести в консоль
    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        var hasAnimalFromOceania = animals.stream()
                .filter(Objects::nonNull)
                .noneMatch(animal -> "Oceania".equalsIgnoreCase(animal.getOrigin()));

        System.out.println(hasAnimalFromOceania);
    }

    // ### Задача №8
    //     Взять всех животных. Отсортировать их породу в стандартном порядке и взять первые 100.
    //     Вывести в консоль возраст самого старого животного
    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        var oldestAnimal = animals.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge));

        System.out.println(oldestAnimal);
    }

    // ### Задача №9
    //     Взять всех животных. Преобразовать их в породы, а породы в []char
    //     Вывести в консоль длину самого короткого массива
    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(Objects::nonNull)
                .map(Animal::getBread)
                .filter(Objects::nonNull)
                .mapToInt(bread -> bread.toCharArray().length)
                .min()
                .ifPresent(System.out::println);
    }

    // ### Задача №10
    //     Взять всех животных. Подсчитать суммарный возраст всех животных. Вывести результат в консоль
    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        var sumAgeForAnimals = animals.stream()
                .filter(Objects::nonNull)
                .map(Animal::getAge)
                .reduce(0, Integer::sum);

        System.out.println(sumAgeForAnimals);
    }

    // ### Задача №11
    //     Взять всех животных. Подсчитать средний возраст всех животных из индонезии (Indonesian). Вывести результат в консоль
    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        var averageAnimalAgeFromIndonesian = animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> "Indonesian".equalsIgnoreCase(animal.getOrigin()))
                .collect(averagingInt(Animal::getAge));

        System.out.println(averageAnimalAgeFromIndonesian);
    }

    // ### Задача №12
    //     Во Французский легион принимают людей со всего света, но есть отбор по полу (только мужчины)
    //     возраст от 18 до 27 лет. Преимущество отдаётся людям военной категории 1, на втором месте - военная категория 2,
    //     и на третьем месте военная категория 3. Отсортировать всех подходящих кандидатов в порядке их
    //     приоритета по военной категории. Однако взять на обучение академия может только 200 человек. Вывести их в консоль.
    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(Objects::nonNull)
                .filter(person -> "Male".equalsIgnoreCase(person.getGender()))
                .filter(filterByAge())
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static Predicate<Person> filterByAge() {
        return person -> {
            var age = getPersonAge(person);
            return 18 <= age && age <= 27;
        };
    }

    private static int getPersonAge(Person person) {
        return Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
    }

    // ### Задача №13
    //     Надвигается цунами и в районе эвакуации требуется в первую очередь обойти дома и эвакуировать больных и раненых (из Hospital),
    //     во вторую очередь детей и стариков (до 18 и пенсионного возраста) а после всех остальных. В первый этап эвакуации мест
    //     в эвакуационном транспорте только 500. Вывести всех людей попадающих в первый этап эвакуации в порядке приоритета (в консоль).
    public static void task13() {
        List<House> houses = Util.getHouses();

        houses.stream()
                .filter(Objects::nonNull)
                .collect(partitioningBy(house -> BUILDING_TYPE_HOSPITAL.equals(house.getBuildingType())))
                .entrySet().stream()
                .flatMap(map->toPersonInfo(map).stream())
                .sorted(Comparator.comparing(PersonInfo::isHospital).reversed()
                        .thenComparing(personInfo -> getPersonAge(personInfo.person) < 18 || getPersonAge(personInfo.person) > 65)
                )
                .map(personInfo -> personInfo.person)
                .limit(500)
                .forEach(System.out::println);
    }

    private record PersonInfo(Boolean isHospital, Person person){};

    private static List<PersonInfo> toPersonInfo(Map.Entry<Boolean, List<House>> map) {
        return map.getValue().stream()
                .flatMap(house -> house.getPersonList().stream())
                .map(person -> new PersonInfo(map.getKey(), person))
                .toList();
    }

    // ### Задача №14
    //     Из перечня автомобилей приходящих на рынок Азии логистическому агентству предстоит отсортировать их в порядке следования
    //     1.Туркменистан - 2.Узбекистан - 3.Казахстан - 4.Кыргызстан - 5.Россия - 6.Монголия.
    //     Все автомобили марки "Jaguar" а так же все авто цвета White идут в первую страну.
    //     Из оставшихся все автомобили с массой до 1500 кг и марок BMW, Lexus, Chrysler и Toyota идут во второй эшелон.
    //     Из оставшихся все автомобили Черного цвета с массой более 4000 кг и все GMC и Dodge идут в третий эшелон.
    //     Из оставшихся все автомобили года выпуска до 1982 или все модели "Civic" и "Cherokee" идут в четвёртый эшелон.
    //     Из оставшихся все автомобили цветов НЕ Yellow, Red, Green и Blue или же по стоимости дороже 40000 в пятый эшелон
    //     Из оставшиеся все автомобили в vin номере которых есть цифра "59" идут в последний шестой эшелон.
    //     Оставшиеся автомобили отбрасываем, они никуда не идут.
    //     Измерить суммарные массы автомобилей всех эшелонов для каждой из стран и подсчитать сколько для каждой страны
    //     будет стоить транспортные расходы если учесть что на 1 тонну транспорта будет потрачено 7.14 $.
    //     Вывести суммарные стоимости в консоль. Вывести общую выручку логистической кампании.
    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    // ### Задача №15
    //     Для оранжереи нужно подобрать растения соответствующие требованиям.
    //     Во-первых, нужно произвести сложную сортировку каталога растений. Отсортировать их по странам происхождения в обратном порядке
    //     После по стоимости и еще по водопотреблению в обратном порядке. Из этого списка взять растения название которых
    //     от буквы "S" до буквы "C". Если растения тенелюбивые и им подходит горшок из стекла, алюминия или стали - то выбираем их.
    //     Далее на каждое растение надо рассчитать стоимость растения + стоимость потребления воды за 5 лет c учётом того
    //     что кубометр воды стоит 1.39 $. Суммировать общую стоимость обслуживания всех растений. Во сколько это обойдётся бюджету?
    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        var price = (Double) flowers.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed()
                )
                .filter(Main::filterByStartSymbolShadowAndPot)
                .mapToDouble(flower -> flower.getPrice() + 5 * 365 * flower.getWaterConsumptionPerDay() * 1.39)
                .sum();

        System.out.println(price);
    }

    private static boolean filterByStartSymbolShadowAndPot(Flower flower) {
        var start = flower.getCommonName().toCharArray()[0];
        if (!('C' <= start && start <= 'S')) return false;
        if (!flower.isShadePreferred()) return false;

        return flower.getFlowerVaseMaterial().stream()
                .anyMatch(material -> material.equalsIgnoreCase("Plexiglass") ||
                        material.equalsIgnoreCase("Aluminum") ||
                        material.equalsIgnoreCase("Steel"));
    }

    // ### Задача №16
    //     Вывод списка студентов младше 18 лет в алфавитном порядке с указанием возраста
    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(Objects::nonNull)
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(student -> System.out.println(student.getSurname() + "   " + student.getAge()));
    }

    // ### Задача №17
    //     Вывод списка групп (без повторений).
    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(Objects::nonNull)
                .map(Student::getGroup)
                .filter(Objects::nonNull)
                .distinct()
                .forEach(System.out::println);
    }

    // ### Задача №18
    //     Определение среднего возраста студентов для каждого факультета.
    //     Выводить название факультета и средний возраст в порядке убывания возраста.
    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(Student::getFaculty, averagingDouble(Student::getAge)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    // ### Задача №19
    //     Вывод списка студентов заданной группы, у которых сдан 3 экзамен (>4).
    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        final String targetGroup = getGroup();

        students.stream()
                .filter(Objects::nonNull)
                .filter(student -> filterStudentByGroupAndExamination3(targetGroup, student, examinations))
                .forEach(System.out::println);
    }

    private static String getGroup() {
        String targetGroup = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            targetGroup = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (targetGroup.isBlank()) {
            throw new RuntimeException("Target group can not be empty");
        }
        return targetGroup;
    }

    private static boolean filterStudentByGroupAndExamination3(String targetGroup, Student student, List<Examination> examinations) {
        if (student.getGroup() == null) return false;
        if (!student.getGroup().equalsIgnoreCase(targetGroup)) return false;
        return examinations.stream()
                .filter(Objects::nonNull)
                .anyMatch(exam -> exam.getStudentId() == student.getId() && exam.getExam3() >= 4);
    }

    // ### Задача №20
    //     Определение факультета с максимальной средней оценкой по первому экзамену.
    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        students.stream()
                .filter(Objects::nonNull)
                .map(student -> buildFacultyExamination(student, examinations))
                .filter(Objects::nonNull)
                .collect(groupingBy(item -> item.faculty, averagingDouble(item -> item.examination.getExam1())))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
    }

    private record FacultyExamination(String faculty, Examination examination) {
    }

    private static FacultyExamination buildFacultyExamination(Student student, List<Examination> examinations) {
        var exam = getExamination(student, examinations);
        if (exam == null) return null;
        return new FacultyExamination(student.getFaculty(), exam);
    }

    private static Examination getExamination(Student student, List<Examination> examinations) {
        if (student == null) return null;
        return examinations.stream()
                .filter(Objects::nonNull)
                .filter(exam -> exam.getStudentId() == student.getId())
                .findAny().orElse(null);
    }

    // ### Задача №21
    //     Определение количества студентов в каждой группе.
    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(Student::getGroup, counting()))
                .forEach((key, value) -> System.out.println(key + "   " + value));
    }

    // ### Задача №22
    //     Определение минимального возраста для каждого факультета.
    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(Student::getFaculty, mapping(Student::getAge, minBy(Integer::compareTo))))
                .forEach((key, value) -> System.out.println(key + "   " + value.orElse(-1)));
    }
}