package com.example.datastructure.algorithm.search;

import net.datafaker.Faker;
import net.datafaker.providers.base.Name;
import net.datafaker.transformations.CsvTransformer;
import net.datafaker.transformations.Schema;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static net.datafaker.transformations.Field.field;

public class SchemaFactory {

    Faker faker = new Faker(new Locale("zh", "CN"));

    AtomicInteger atomicInteger = new AtomicInteger();

    public Schema<Name, String> newSchema() {
        // name gender phone address birthday
//        System.out.println(faker.name().fullName());
//        System.out.println(faker.gender().binaryTypes());
//        System.out.println(faker.phoneNumber().cellPhone());
//        System.out.println(faker.date().birthday("yyyy-MM-dd hh:mm:ss"));
//        System.out.println(faker.address().cityName());
        return Schema.of(
                field("number", () -> String.valueOf(atomicInteger.getAndIncrement())),
                field("name", () -> faker.name().fullName()),
                field("gender", () -> faker.gender().binaryTypes()),
                field("phone", () -> faker.phoneNumber().cellPhone()),
                field("address", () -> faker.address().cityName()),
                field("birthday", () -> faker.date().birthday("yyyy-MM-dd hh:mm:ss"))
        );
    }

    public void generate(int limit) {
        // transformer could be the same for both
        CsvTransformer<Name> transformer = CsvTransformer.<Name>builder().header(true).separator(",").build();
        // Schema for from scratch
        Schema<Name, String> fromScratch = Schema.of(field("firstName", () -> faker.name().firstName()), field("lastname", () -> faker.name().lastName()));
        System.out.println(transformer.generate(fromScratch, limit));
        // Schema for transformations
        Schema<Name, String> schemaForTransformations = Schema.of(field("firstName", Name::firstName), field("lastname", Name::lastName));
        // Here we pass a collection of Name objects and extract first and lastnames from each element
        System.out.println(transformer.generate(faker.collection(faker::name).maxLen(limit).generate(), schemaForTransformations));
    }

}
