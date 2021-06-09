package nl.aartj.GarageApp.Product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner configProducts(ProductRepository repository) {
        return args -> {

            Product MOT = new Product(
                    1L,
                    "APK",
                    35.00
            );

            Product serviceSmall = new Product(
                    2L,
                    "Onderhoudsbeurt Klein",
                    200.00
            );
            Product serviceLarge = new Product(
                    3L,
                    "Onderhoudsbeurt Groot",
                    400.00
            );

            Product fixGearBox = new Product(
                    4L,
                    "Versnellingsbak vervangen",
                    2000.00
            );

            Product fixClutchPlate = new Product(
                    5L,
                    "Koppelingsplaat vervangen",
                    500.00
            );

            Product fixTimingBelt = new Product(
                    6L,
                    "Distributieriem vervangen",
                    400.00
            );

            Product fixDrivingBelt = new Product(
                    7L,
                    "Multiriem vervangen",
                    125.00
            );

            Product fixWaterPump = new Product(
                    8L,
                    "Waterpomp vervangen",
                    300.00
            );

            Product fixExhaustPipe = new Product(
                    9L,
                    "Uitlaat vervangen",
                    250.00
            );

            Product fixBrakePads = new Product(
                    10L,
                    "Remblokken vervangen",
                    250.00
            );

            Product fixBrakeDiscs = new Product(
                    11L,
                    "Remschijven vervangen",
                    350.00
            );

            Product fixEngine = new Product(
                    12L,
                    "Motorblok repareren",
                    1000.00
            );

            Product fixTire = new Product(
                    13L,
                    "Band plakken",
                    10.00
            );

            Product changeTires = new Product(
                    14L,
                    "Banden vervangen",
                    100.00
            );

            Product fixHeadLights = new Product(
                    15L,
                    "Koplampen vervangen",
                    25.00
            );

            Product fixTailLights = new Product(
                    16L,
                    "Achterlichten vervangen",
                    25.00
            );

            Product fixWipers = new Product(
                    17L,
                    "Ruitenwissers vervangen",
                    50.00
            );

            Product fillAircoFluids = new Product(
                    18L,
                    "Bijvullen airco",
                    75.00
            );

            Product fixWindshield = new Product(
                    19L,
                    "Vooruit repareren",
                    90.00
            );

            Product changeWindShield = new Product(
                    20L,
                    "Voorruit vervangen",
                    375.00
            );

            Product fixRearWindow = new Product(
                    21L,
                    "Achterruit repareren",
                    80.00
            );

            Product changeRearWindow = new Product(
                    22L,
                    "Achterruit vervangen",
                    120.00
            );

            Product changeSideWindows = new Product(
                    23L,
                    "Zijramen vervangen",
                    400.00
            );

            repository.saveAll(
                    List.of(MOT, serviceSmall, serviceLarge, fixGearBox,
                            fixClutchPlate, fixTimingBelt, fixDrivingBelt,
                            fixWaterPump, fixExhaustPipe, fixBrakePads,
                            fixBrakeDiscs, fixEngine, fixTire, changeTires,
                            fixHeadLights, fixTailLights, fixWipers,
                            fillAircoFluids, fixWindshield, changeWindShield,
                            fixRearWindow, changeRearWindow, changeSideWindows)
            );
        };
    }
}

