package Problems.set1;

import java.util.*;

class Person {
    String name;
    String gender;
    Person father;
    Person mother;
    List<Person> children = new ArrayList<>();

    Person(String name, String gender, Person father, Person mother) {
        this.name = name;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        if (father != null) {
            father.children.add(this);
        }
        if (mother != null) {
            mother.children.add(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}

class FamilyTree {
    Map<String, Person> people = new HashMap<>();

    FamilyTree(List<Person> people) {
        for (Person person : people) {
            this.people.put(person.name, person);
        }
    }

    List<String> getEligiblePartners(String name) {
        Person person = people.get(name);
        List<String> eligiblePartners = new ArrayList<>();
        if (person == null) {
            return eligiblePartners;
        }
        if (person.gender.equals("Male")) {
            eligiblePartners.addAll(getDaughters(person.father.father));
            eligiblePartners.addAll(getDaughters(person.mother));
        } else {
            eligiblePartners.addAll(getSons(person.father.mother));
            eligiblePartners.addAll(getSons(person.mother.father));
        }
        return eligiblePartners;
    }

    List<String> getDaughters(Person person) {

        List<String> daughters = new ArrayList<>();
        if (person == null) {
            return daughters;
        }
        for (Person child : person.children) {
            if (child.gender.equals("Female")) {
                    for (Person p :child.children)
                    {
                        daughters.add(p.name);
                    }
            }
        }
        return daughters;
    }

    List<String> getSons(Person person) {
        List<String> sons = new ArrayList<>();
        if (person == null) {
            return sons;
        }
        for (Person child : person.children) {
            if (child.gender.equals("Male")) {
                for (Person p :child.children)
                {
                    sons.add(p.name);
                }
            }
        }
        return sons;
    }
}

public class Main {
    public static void main(String[] args) {
        Person brad = new Person("Brad", "Male", null, null);
        Person lisa = new Person("Lisa", "Female", null, null);
        Person jenny = new Person("Jenny", "Female", null, null);
        Person steve = new Person("Steve", "Male", null, null);
        Person john = new Person("John", "Male", brad, lisa);
        Person emma = new Person("Emma", "Female", brad, lisa);
        Person alex = new Person("Alex", "Male", john, jenny);
        Person emily = new Person("Emily", "Female", steve, emma);
        Person rachel = new Person("Rachel", "Female", steve, emma);
        List<Person> people = Arrays.asList(john, brad, lisa, jenny, emma, alex, steve, emily, rachel);
        FamilyTree familyTree = new FamilyTree(people);
        List<String> eligiblePartners = familyTree.getEligiblePartners("Alex");
        for (String eligiblePartner : eligiblePartners) {
            System.out.println(eligiblePartner);
        }
    }
}

