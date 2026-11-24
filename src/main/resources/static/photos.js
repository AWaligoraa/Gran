const TRAIL_PHOTOS = {
    // istniejące
    1: "/images/orla.jpg",
    2: "/images/szpiglasowa.jpg",
    3: "/images/czarny-staw.jpg",
    4: "/images/dolina-koscieliska.jpg",
    5: "/images/polana-chocholowska.jpg",
    9: "/images/rysy.jpg",

    // Dolina Pięciu Stawów
    11: "/images/dolina-pieciu-stawow.jpg",

    // Morskie Oko (oba warianty)
    12: "/images/morskie-oko.jpg",
    13: "/images/morskie-oko.jpg",

    // Czarny Staw pod Rysami
    14: "/images/czarny-staw-pod-rysami.jpg",

    // Nosal
    15: "/images/nosal.jpg",

    // Orla Perć (oba warianty — istniejące zdjęcie)
    16: "/images/orla.jpg",

    // Giewont (3 warianty)
    17: "/images/giewont.jpg",
    18: "/images/giewont.jpg",
    19: "/images/giewont.jpg",

    // Kasprowy
    20: "/images/kasprowy.jpg",

    // Zawrat (2 warianty)
    21: "/images/zawrat.jpg",
    22: "/images/zawrat.jpg",

    // Krzyżne (2 warianty)
    23: "/images/krzyzne.jpg",
    24: "/images/krzyzne.jpg",

    // Świnica (2 warianty)
    25: "/images/swinica.jpg",
    26: "/images/swinica.jpg",

    // Kozi / Granaty / Chłopek
    27: "/images/kozi-wierch.jpg",
    28: "/images/zadni-granat.jpg",
    29: "/images/skrajny-granat.jpg",
    30: "/images/mieguszowiecka.jpg",

    // Wrota Chałubińskiego
    31: "/images/wrota-chalubinskiego.jpg",

    // Gęsia Szyja (oba warianty)
    32: "/images/gesia-szyja.png",
    33: "/images/gesia-szyja.png"
};

function trailPhoto(id) {
    const path = TRAIL_PHOTOS[Number(id)] || "/images/fallback.jpg";
    return path + "?v=7";
}
