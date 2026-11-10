const TRAIL_PHOTOS = {
    1: "/images/orla.jpg",
    2: "/images/szpiglasowa.jpg",
    3: "/images/czarny-staw.jpg",
    4: "/images/dolina-koscieliska.jpg",
    5: "/images/polana-chocholowska.jpg",
    9: "/images/rysy.jpg"
};

function trailPhoto(id) {
    const path = TRAIL_PHOTOS[Number(id)] || "/images/fallback.jpg";
    return path + "?v=5";
}
