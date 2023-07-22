package fr.codecrafters.infiniteutopia.item;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.item.cooking.CooksCarver;
import fr.codecrafters.infiniteutopia.item.elements.ChemicalElement;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsManager {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteUtopia.MOD_ID);

    public static final RegistryObject<Item> COOKS_CARVER = ITEMS.register("cooks_carver",
            () -> new CooksCarver(new Item.Properties().durability(128)));

    public static final RegistryObject<ChemicalElement> HYDROGEN = ITEMS.register("elements/hydrogen",
            () -> new ChemicalElement("hydrogen", 1, 1.008, 14.01, 20.28, 0.08375));

    public static final RegistryObject<ChemicalElement> HELIUM = ITEMS.register("elements/helium",
            () -> new ChemicalElement("helium", 2, 4.002602, 0.95, 4.22, 0.1785));

    public static final RegistryObject<ChemicalElement> LITHIUM = ITEMS.register("elements/lithium",
            () -> new ChemicalElement("lithium", 3, 6.94, 453.65, 1615, 0.534));

    public static final RegistryObject<ChemicalElement> BERYLLIUM = ITEMS.register("elements/beryllium",
            () -> new ChemicalElement("beryllium", 4, 9.0121831, 1560, 2742, 1.85));

    public static final RegistryObject<ChemicalElement> BORON = ITEMS.register("elements/boron",
            () -> new ChemicalElement("boron", 5, 10.81, 2349, 4200, 2.34));

    public static final RegistryObject<ChemicalElement> CARBON = ITEMS.register("elements/carbon",
            () -> new ChemicalElement("carbon", 6, 12.011, 3800, 4300, 2.267));

    public static final RegistryObject<ChemicalElement> NITROGEN = ITEMS.register("elements/nitrogen",
            () -> new ChemicalElement("nitrogen", 7, 14.007, 63.15, 77.36, 0.001251));

    public static final RegistryObject<ChemicalElement> OXYGEN = ITEMS.register("elements/oxygen",
            () -> new ChemicalElement("oxygen", 8, 15.999, 54.36, 90.20, 0.001429));

    public static final RegistryObject<ChemicalElement> FLUORINE = ITEMS.register("elements/fluorine",
            () -> new ChemicalElement("fluorine", 9, 18.998403163, 53.53, 85.03, 0.001696));

    public static final RegistryObject<ChemicalElement> NEON = ITEMS.register("elements/neon",
            () -> new ChemicalElement("neon", 10, 20.1797, 24.56, 27.07, 0.0008999));

    public static final RegistryObject<ChemicalElement> SODIUM = ITEMS.register("elements/sodium",
            () -> new ChemicalElement("sodium", 11, 22.98976928, 370.87, 1156, 0.971));

    public static final RegistryObject<ChemicalElement> MAGNESIUM = ITEMS.register("elements/magnesium",
            () -> new ChemicalElement("magnesium", 12, 24.305, 923, 1363, 1.738));

    public static final RegistryObject<ChemicalElement> ALUMINIUM = ITEMS.register("elements/aluminium",
            () -> new ChemicalElement("aluminium", 13, 26.9815385, 933.47, 2792, 2.6989));

    public static final RegistryObject<ChemicalElement> SILICON = ITEMS.register("elements/silicon",
            () -> new ChemicalElement("silicon", 14, 28.085, 1687, 3538, 2.3296));

    public static final RegistryObject<ChemicalElement> PHOSPHORUS = ITEMS.register("elements/phosphorus",
            () -> new ChemicalElement("phosphorus", 15, 30.973761998, 317.30, 553.65, 1.82));

    public static final RegistryObject<ChemicalElement> SULFUR = ITEMS.register("elements/sulfur",
            () -> new ChemicalElement("sulfur", 16, 32.06, 388.36, 717.87, 2.067));

    public static final RegistryObject<ChemicalElement> CHLORINE = ITEMS.register("elements/chlorine",
            () -> new ChemicalElement("chlorine", 17, 35.45, 171.65, 239.11, 0.003214));

    public static final RegistryObject<ChemicalElement> ARGON = ITEMS.register("elements/argon",
            () -> new ChemicalElement("argon", 18, 39.948, 83.80, 87.30, 0.0017837));

    public static final RegistryObject<ChemicalElement> POTASSIUM = ITEMS.register("elements/potassium",
            () -> new ChemicalElement("potassium", 19, 39.0983, 336.53, 1032, 0.862));

    public static final RegistryObject<ChemicalElement> CALCIUM = ITEMS.register("elements/calcium",
            () -> new ChemicalElement("calcium", 20, 40.078, 1115, 1757, 1.54));

    public static final RegistryObject<ChemicalElement> SCANDIUM = ITEMS.register("elements/scandium",
            () -> new ChemicalElement("scandium", 21, 44.955908, 1814, 3109, 2.989));

    public static final RegistryObject<ChemicalElement> TITANIUM = ITEMS.register("elements/titanium",
            () -> new ChemicalElement("titanium", 22, 47.867, 1941, 3560, 4.506));

    public static final RegistryObject<ChemicalElement> VANADIUM = ITEMS.register("elements/vanadium",
            () -> new ChemicalElement("vanadium", 23, 50.9415, 2183, 3680, 6.11));

    public static final RegistryObject<ChemicalElement> CHROMIUM = ITEMS.register("elements/chromium",
            () -> new ChemicalElement("chromium", 24, 51.9961, 2180, 2944, 7.15));

    public static final RegistryObject<ChemicalElement> MANGANESE = ITEMS.register("elements/manganese",
            () -> new ChemicalElement("manganese", 25, 54.938044, 1519, 2334, 7.44));

    public static final RegistryObject<ChemicalElement> IRON = ITEMS.register("elements/iron",
            () -> new ChemicalElement("iron", 26, 55.845, 1811, 3134, 7.874));

    public static final RegistryObject<ChemicalElement> COBALT = ITEMS.register("elements/cobalt",
            () -> new ChemicalElement("cobalt", 27, 58.933194, 1768, 3200, 8.86));

    public static final RegistryObject<ChemicalElement> NICKEL = ITEMS.register("elements/nickel",
            () -> new ChemicalElement("nickel", 28, 58.6934, 1728, 3186, 8.912));

    public static final RegistryObject<ChemicalElement> COPPER = ITEMS.register("elements/copper",
            () -> new ChemicalElement("copper", 29, 63.546, 1357.77, 2835, 8.96));

    public static final RegistryObject<ChemicalElement> ZINC = ITEMS.register("elements/zinc",
            () -> new ChemicalElement("zinc", 30, 65.38, 692.68, 1180, 7.134));

    public static final RegistryObject<ChemicalElement> GALLIUM = ITEMS.register("elements/gallium",
            () -> new ChemicalElement("gallium", 31, 69.723, 302.91, 2477, 5.907));

    public static final RegistryObject<ChemicalElement> GERMANIUM = ITEMS.register("elements/germanium",
            () -> new ChemicalElement("germanium", 32, 72.63, 1211.40, 3106, 5.323));

    public static final RegistryObject<ChemicalElement> ARSENIC = ITEMS.register("elements/arsenic",
            () -> new ChemicalElement("arsenic", 33, 74.921595, 1090, 887, 5.776));

    public static final RegistryObject<ChemicalElement> SELENIUM = ITEMS.register("elements/selenium",
            () -> new ChemicalElement("selenium", 34, 78.971, 494, 958, 4.809));

    public static final RegistryObject<ChemicalElement> BROMINE = ITEMS.register("elements/bromine",
            () -> new ChemicalElement("bromine", 35, 79.904, 265.8, 332, 3.122));

    public static final RegistryObject<ChemicalElement> KRYPTON = ITEMS.register("elements/krypton",
            () -> new ChemicalElement("krypton", 36, 83.798, 115.79, 119.93, 0.003733));

    public static final RegistryObject<ChemicalElement> RUBIDIUM = ITEMS.register("elements/rubidium",
            () -> new ChemicalElement("rubidium", 37, 85.4678, 312.46, 961, 1.532));

    public static final RegistryObject<ChemicalElement> STRONTIUM = ITEMS.register("elements/strontium",
            () -> new ChemicalElement("strontium", 38, 87.62, 1050, 1655, 2.64));

    public static final RegistryObject<ChemicalElement> YTTRIUM = ITEMS.register("elements/yttrium",
            () -> new ChemicalElement("yttrium", 39, 88.90584, 1799, 3609, 4.469));

    public static final RegistryObject<ChemicalElement> ZIRCONIUM = ITEMS.register("elements/zirconium",
            () -> new ChemicalElement("zirconium", 40, 91.224, 2128, 4682, 6.506));

    public static final RegistryObject<ChemicalElement> NIOBIUM = ITEMS.register("elements/niobium",
            () -> new ChemicalElement("niobium", 41, 92.90637, 2750, 5017, 8.57));

    public static final RegistryObject<ChemicalElement> MOLYBDENUM = ITEMS.register("elements/molybdenum",
            () -> new ChemicalElement("molybdenum", 42, 95.95, 2896, 4912, 10.22));

    public static final RegistryObject<ChemicalElement> TECHNETIUM = ITEMS.register("elements/technetium",
            () -> new ChemicalElement("technetium", 43, 98, 2430, 4538, 11.5));

    public static final RegistryObject<ChemicalElement> RUTHENIUM = ITEMS.register("elements/ruthenium",
            () -> new ChemicalElement("ruthenium", 44, 101.07, 2607, 4423, 12.37));

    public static final RegistryObject<ChemicalElement> RHODIUM = ITEMS.register("elements/rhodium",
            () -> new ChemicalElement("rhodium", 45, 102.90550, 2237, 3968, 12.41));

    public static final RegistryObject<ChemicalElement> PALLADIUM = ITEMS.register("elements/palladium",
            () -> new ChemicalElement("palladium", 46, 106.42, 1828.05, 3236, 12.02));

    public static final RegistryObject<ChemicalElement> SILVER = ITEMS.register("elements/silver",
            () -> new ChemicalElement("silver", 47, 107.8682, 1234.93, 2435, 10.501));

    public static final RegistryObject<ChemicalElement> CADMIUM = ITEMS.register("elements/cadmium",
            () -> new ChemicalElement("cadmium", 48, 112.414, 594.22, 1040, 8.69));

    public static final RegistryObject<ChemicalElement> INDIUM = ITEMS.register("elements/indium",
            () -> new ChemicalElement("indium", 49, 114.818, 429.75, 2345, 7.31));

    public static final RegistryObject<ChemicalElement> TIN = ITEMS.register("elements/tin",
            () -> new ChemicalElement("tin", 50, 118.710, 505.08, 2875, 7.287));

    public static final RegistryObject<ChemicalElement> ANTIMONY = ITEMS.register("elements/antimony",
            () -> new ChemicalElement("antimony", 51, 121.760, 903.78, 1860, 6.685));

    public static final RegistryObject<ChemicalElement> TELLURIUM = ITEMS.register("elements/tellurium",
            () -> new ChemicalElement("tellurium", 52, 127.60, 722.66, 1261, 6.232));

    public static final RegistryObject<ChemicalElement> IODINE = ITEMS.register("elements/iodine",
            () -> new ChemicalElement("iodine", 53, 126.90447, 386.85, 457.4, 4.93));

    public static final RegistryObject<ChemicalElement> XENON = ITEMS.register("elements/xenon",
            () -> new ChemicalElement("xenon", 54, 131.293, 161.36, 165.03, 0.005887));

    public static final RegistryObject<ChemicalElement> CESIUM = ITEMS.register("elements/cesium",
            () -> new ChemicalElement("cesium", 55, 132.90545196, 301.59, 944, 1.873));

    public static final RegistryObject<ChemicalElement> BARIUM = ITEMS.register("elements/barium",
            () -> new ChemicalElement("barium", 56, 137.327, 1000, 2170, 3.594));

    public static final RegistryObject<ChemicalElement> LANTHANUM = ITEMS.register("elements/lanthanum",
            () -> new ChemicalElement("lanthanum", 57, 138.90547, 1193, 3737, 6.145));

    public static final RegistryObject<ChemicalElement> CERIUM = ITEMS.register("elements/cerium",
            () -> new ChemicalElement("cerium", 58, 140.116, 1068, 3716, 6.77));

    public static final RegistryObject<ChemicalElement> PRASEODYMIUM = ITEMS.register("elements/praseodymium",
            () -> new ChemicalElement("praseodymium", 59, 140.90766, 1208, 3793, 6.773));

    public static final RegistryObject<ChemicalElement> NEODYMIUM = ITEMS.register("elements/neodymium",
            () -> new ChemicalElement("neodymium", 60, 144.242, 1297, 3347, 7.007));

    public static final RegistryObject<ChemicalElement> PROMETHIUM = ITEMS.register("elements/promethium",
            () -> new ChemicalElement("promethium", 61, 145, 1315, 3273, 7.26));

    public static final RegistryObject<ChemicalElement> SAMARIUM = ITEMS.register("elements/samarium",
            () -> new ChemicalElement("samarium", 62, 150.36, 1345, 2067, 7.52));

    public static final RegistryObject<ChemicalElement> EUROPIUM = ITEMS.register("elements/europium",
            () -> new ChemicalElement("europium", 63, 151.964, 1099, 1802, 5.243));

    public static final RegistryObject<ChemicalElement> GADOLINIUM = ITEMS.register("elements/gadolinium",
            () -> new ChemicalElement("gadolinium", 64, 157.25, 1585, 3546, 7.895));

    public static final RegistryObject<ChemicalElement> TERBIUM = ITEMS.register("elements/terbium",
            () -> new ChemicalElement("terbium", 65, 158.92535, 1629, 3503, 8.229));

    public static final RegistryObject<ChemicalElement> DYSPROSIUM = ITEMS.register("elements/dysprosium",
            () -> new ChemicalElement("dysprosium", 66, 162.500, 1680, 2840, 8.55));

    public static final RegistryObject<ChemicalElement> HOLMIUM = ITEMS.register("elements/holmium",
            () -> new ChemicalElement("holmium", 67, 164.93033, 1734, 2993, 8.795));

    public static final RegistryObject<ChemicalElement> ERBIUM = ITEMS.register("elements/erbium",
            () -> new ChemicalElement("erbium", 68, 167.259, 1802, 3141, 9.066));

    public static final RegistryObject<ChemicalElement> THULIUM = ITEMS.register("elements/thulium",
            () -> new ChemicalElement("thulium", 69, 168.93422, 1818, 2223, 9.321));

    public static final RegistryObject<ChemicalElement> YTTERBIUM = ITEMS.register("elements/ytterbium",
            () -> new ChemicalElement("ytterbium", 70, 173.045, 1097, 1469, 6.965));

    public static final RegistryObject<ChemicalElement> LUTETIUM = ITEMS.register("elements/lutetium",
            () -> new ChemicalElement("lutetium", 71, 174.9668, 1925, 3675, 9.841));

    public static final RegistryObject<ChemicalElement> HAFNIUM = ITEMS.register("elements/hafnium",
            () -> new ChemicalElement("hafnium", 72, 178.49, 2506, 4876, 13.31));

    public static final RegistryObject<ChemicalElement> TANTALUM = ITEMS.register("elements/tantalum",
            () -> new ChemicalElement("tantalum", 73, 180.94788, 3290, 5731, 16.654));

    public static final RegistryObject<ChemicalElement> TUNGSTEN = ITEMS.register("elements/tungsten",
            () -> new ChemicalElement("tungsten", 74, 183.84, 3695, 5828, 19.25));

    public static final RegistryObject<ChemicalElement> RHENIUM = ITEMS.register("elements/rhenium",
            () -> new ChemicalElement("rhenium", 75, 186.207, 3459, 5869, 21.02));

    public static final RegistryObject<ChemicalElement> OSMIUM = ITEMS.register("elements/osmium",
            () -> new ChemicalElement("osmium", 76, 190.23, 3306, 5285, 22.61));

    public static final RegistryObject<ChemicalElement> IRIDIUM = ITEMS.register("elements/iridium",
            () -> new ChemicalElement("iridium", 77, 192.217, 2719, 4701, 22.56));

    public static final RegistryObject<ChemicalElement> PLATINUM = ITEMS.register("elements/platinum",
            () -> new ChemicalElement("platinum", 78, 195.084, 2041.4, 4098, 21.45));

    public static final RegistryObject<ChemicalElement> GOLD = ITEMS.register("elements/gold",
            () -> new ChemicalElement("gold", 79, 196.966569, 1337.33, 3129, 19.282));

    public static final RegistryObject<ChemicalElement> MERCURY = ITEMS.register("elements/mercury",
            () -> new ChemicalElement("mercury", 80, 200.592, 234.32, 629.88, 13.5336));

    public static final RegistryObject<ChemicalElement> THALLIUM = ITEMS.register("elements/thallium",
            () -> new ChemicalElement("thallium", 81, 204.38, 577, 1746, 11.85));

    public static final RegistryObject<ChemicalElement> LEAD = ITEMS.register("elements/lead",
            () -> new ChemicalElement("lead", 82, 207.2, 600.61, 2022, 11.342));

    public static final RegistryObject<ChemicalElement> BISMUTH = ITEMS.register("elements/bismuth",
            () -> new ChemicalElement("bismuth", 83, 208.98040, 544.55, 1837, 9.807));

    public static final RegistryObject<ChemicalElement> POLONIUM = ITEMS.register("elements/polonium",
            () -> new ChemicalElement("polonium", 84, 209, 527, 1235, 9.32));

    public static final RegistryObject<ChemicalElement> ASTATINE = ITEMS.register("elements/astatine",
            () -> new ChemicalElement("astatine", 85, 210, 575, 610, 7));

    public static final RegistryObject<ChemicalElement> RADON = ITEMS.register("elements/radon",
            () -> new ChemicalElement("radon", 86, 222, 202, 211.3, 0.00973));

    public static final RegistryObject<ChemicalElement> FRANCIUM = ITEMS.register("elements/francium",
            () -> new ChemicalElement("francium", 87, 223, 300, 950, 1.87));

    public static final RegistryObject<ChemicalElement> RADIUM = ITEMS.register("elements/radium",
            () -> new ChemicalElement("radium", 88, 226, 973, 2010, 5.5));

    public static final RegistryObject<ChemicalElement> ACTINIUM = ITEMS.register("elements/actinium",
            () -> new ChemicalElement("actinium", 89, 227, 1323, 3471, 10.07));

    public static final RegistryObject<ChemicalElement> THORIUM = ITEMS.register("elements/thorium",
            () -> new ChemicalElement("thorium", 90, 232.0377, 2023, 5061, 11.72));

    public static final RegistryObject<ChemicalElement> PROTACTINIUM = ITEMS.register("elements/protactinium",
            () -> new ChemicalElement("protactinium", 91, 231.03588, 1841, 4300, 15.37));

    public static final RegistryObject<ChemicalElement> URANIUM = ITEMS.register("elements/uranium",
            () -> new ChemicalElement("uranium", 92, 238.02891, 1405.3, 4404, 19.16));

    public static final RegistryObject<ChemicalElement> NEPTUNIUM = ITEMS.register("elements/neptunium",
            () -> new ChemicalElement("neptunium", 93, 237, 917, 4273, 20.45));

    public static final RegistryObject<ChemicalElement> PLUTONIUM = ITEMS.register("elements/plutonium",
            () -> new ChemicalElement("plutonium", 94, 244, 912.5, 3505, 19.84));

    public static final RegistryObject<ChemicalElement> AMERICIUM = ITEMS.register("elements/americium",
            () -> new ChemicalElement("americium", 95, 243, 1449, 2880, 13.67));

    public static final RegistryObject<ChemicalElement> CURIUM = ITEMS.register("elements/curium",
            () -> new ChemicalElement("curium", 96, 247, 1613, 3383, 13.51));

    public static final RegistryObject<ChemicalElement> BERKELIUM = ITEMS.register("elements/berkelium",
            () -> new ChemicalElement("berkelium", 97, 247, 1259, 2900, 14.79));

    public static final RegistryObject<ChemicalElement> CALIFORNIUM = ITEMS.register("elements/californium",
            () -> new ChemicalElement("californium", 98, 251, 1173, 1743, 15.1));

    public static final RegistryObject<ChemicalElement> EINSTEINIUM = ITEMS.register("elements/einsteinium",
            () -> new ChemicalElement("einsteinium", 99, 252, 1133, 1269, 8.84));

    public static final RegistryObject<ChemicalElement> FERMIUM = ITEMS.register("elements/fermium",
            () -> new ChemicalElement("fermium", 100, 257, 1800, 1100, 9.7));

    public static final RegistryObject<ChemicalElement> MENDELEVIUM = ITEMS.register("elements/mendelevium",
            () -> new ChemicalElement("mendelevium", 101, 258, 1100, 1100, 10.3));

    public static final RegistryObject<ChemicalElement> NOBELIUM = ITEMS.register("elements/nobelium",
            () -> new ChemicalElement("nobelium", 102, 259, 1100, 1100, 9.9));

    public static final RegistryObject<ChemicalElement> LAWRENCIUM = ITEMS.register("elements/lawrencium",
            () -> new ChemicalElement("lawrencium", 103, 266, 1900, 1100, 15.6));

    public static final RegistryObject<ChemicalElement> RUTHERFORDIUM = ITEMS.register("elements/rutherfordium",
            () -> new ChemicalElement("rutherfordium", 104, 267, 2400, 5800, 23.2));

    public static final RegistryObject<ChemicalElement> DUBNIUM = ITEMS.register("elements/dubnium",
            () -> new ChemicalElement("dubnium", 105, 268, 1900, 1100, 29.3));

    public static final RegistryObject<ChemicalElement> SEABORGIUM = ITEMS.register("elements/seaborgium",
            () -> new ChemicalElement("seaborgium", 106, 269, 1900, 1100, 35.0));

    public static final RegistryObject<ChemicalElement> BOHRIUM = ITEMS.register("elements/bohrium",
            () -> new ChemicalElement("bohrium", 107, 270, 1900, 1100, 37.1));

    public static final RegistryObject<ChemicalElement> HASSIUM = ITEMS.register("elements/hassium",
            () -> new ChemicalElement("hassium", 108, 269, 1900, 1100, 40.7));

    public static final RegistryObject<ChemicalElement> MEITNERIUM = ITEMS.register("elements/meitnerium",
            () -> new ChemicalElement("meitnerium", 109, 278, 1900, 1100, 37.4));

    public static final RegistryObject<ChemicalElement> DARMSTADTIUM = ITEMS.register("elements/darmstadtium",
            () -> new ChemicalElement("darmstadtium", 110, 281, 1900, 1100, 34.8));

    public static final RegistryObject<ChemicalElement> ROENTGENIUM = ITEMS.register("elements/roentgenium",
            () -> new ChemicalElement("roentgenium", 111, 282, 1900, 1100, 28.7));

    public static final RegistryObject<ChemicalElement> COPERNICIUM = ITEMS.register("elements/copernicium",
            () -> new ChemicalElement("copernicium", 112, 285, 1900, 1100, 23.7));

    public static final RegistryObject<ChemicalElement> NIHONIUM = ITEMS.register("elements/nihonium",
            () -> new ChemicalElement("nihonium", 113, 286, 1900, 1100, 16));

    public static final RegistryObject<ChemicalElement> FLEROVIUM = ITEMS.register("elements/flerovium",
            () -> new ChemicalElement("flerovium", 114, 289, 1900, 1100, 14));

    public static final RegistryObject<ChemicalElement> MOSCOVIUM = ITEMS.register("elements/moscovium",
            () -> new ChemicalElement("moscovium", 115, 290, 1900, 1100, 13.5));

    public static final RegistryObject<ChemicalElement> LIVERMORIUM = ITEMS.register("elements/livermorium",
            () -> new ChemicalElement("livermorium", 116, 293, 1900, 1100, 12.9));

    public static final RegistryObject<ChemicalElement> TENNESSINE = ITEMS.register("elements/tennessine",
            () -> new ChemicalElement("tennessine", 117, 294, 1900, 1100, 7.2));

    public static final RegistryObject<ChemicalElement> OGANESSON = ITEMS.register("elements/oganesson",
            () -> new ChemicalElement("oganesson", 118, 294, 1900, 1100, 5.0));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



}
