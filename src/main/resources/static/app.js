const filters = [
    ["min-distance", "max-distance", "distance-value", "distance-fill", " km"],
    ["min-technical", "max-technical", "technical-value", "technical-fill", ""],
    ["min-exposure", "max-exposure", "exposure-value", "exposure-fill", ""],
    ["min-condition", "max-condition", "condition-value", "condition-fill", ""]
];

const card = document.querySelector("#card");
const previousButton = document.querySelector("#previous");
const nextButton = document.querySelector("#next");

const searchButton = document.querySelector("#search");

let trails = [];
let index = 0;

filters.forEach(([minId, maxId, valueId, fillId, suffix]) => {
    const minInput = document.querySelector("#" + minId);
    const maxInput = document.querySelector("#" + maxId);
    const value = document.querySelector("#" + valueId);
    const fill = document.querySelector("#" + fillId);

    function sync(changed) {
        if (Number(minInput.value) > Number(maxInput.value)) {
            if (changed === minInput) {
                maxInput.value = minInput.value;
            } else {
                minInput.value = maxInput.value;
            }
        }
        const lower = Number(minInput.min);
        const upper = Number(minInput.max);
        const start = (Number(minInput.value) - lower) / (upper - lower);
        const end = (Number(maxInput.value) - lower) / (upper - lower);
        fill.style.left = (start * 100) + "%";
        fill.style.width = ((end - start) * 100) + "%";
        value.textContent = formatNumber(minInput.value) + " – " + formatNumber(maxInput.value) + suffix;
    }

    minInput.addEventListener("input", () => sync(minInput));
    maxInput.addEventListener("input", () => sync(maxInput));
    sync(minInput);
});

loadTrails();

searchButton.addEventListener("click", loadTrails);

previousButton.addEventListener("click", () => show(index - 1));
nextButton.addEventListener("click", () => show(index + 1));

document.addEventListener("keydown", (event) => {
    if (event.target.matches("input")) {
        return;
    }
    if (event.key === "ArrowLeft") {
        show(index - 1);
    }
    if (event.key === "ArrowRight") {
        show(index + 1);
    }
});

async function loadTrails() {
    const params = new URLSearchParams({
        minDistance: valueOf("min-distance"),
        maxDistance: valueOf("max-distance"),
        minTechnical: valueOf("min-technical"),
        maxTechnical: valueOf("max-technical"),
        minExposure: valueOf("min-exposure"),
        maxExposure: valueOf("max-exposure"),
        minCondition: valueOf("min-condition"),
        maxCondition: valueOf("max-condition")
    });
    const response = await fetch("/api/trails?" + params);
    trails = await response.json();
    index = 0;
    render();
}

function show(nextIndex) {
    if (trails.length === 0) {
        return;
    }
    index = (nextIndex + trails.length) % trails.length;
    render();
}

function render() {
    const canBrowse = trails.length > 1;
    previousButton.disabled = !canBrowse;
    nextButton.disabled = !canBrowse;

    if (trails.length === 0) {
        card.innerHTML = '<p class="empty">Żaden szlak nie pasuje do tych filtrów.</p>';
        return;
    }

    const trail = trails[index];
    card.innerHTML = `
        <h2>${escapeHtml(trail.name)}</h2>
        <div class="photo" aria-hidden="true">
            <svg viewBox="0 0 640 220" preserveAspectRatio="xMidYMax slice">
                <rect width="640" height="220" fill="#d7e0db"/>
                <path d="M0 150 L90 92 L150 124 L230 58 L310 118 L390 74 L470 132 L560 86 L640 128 L640 220 L0 220 Z" fill="#31423b"/>
                <path d="M0 176 L120 142 L210 168 L330 124 L430 166 L530 140 L640 172 L640 220 L0 220 Z" fill="#1d2823"/>
            </svg>
        </div>
        <hr>
        <dl class="stats">
            <div><dt>Start</dt><dd>${escapeHtml(trail.startPoint)}</dd></div>
            <div><dt>Dystans</dt><dd>${formatNumber(trail.distance)} km</dd></div>
            <div><dt>Trudność techniczna</dt><dd>${formatNumber(trail.technicalDifficulty)}</dd></div>
            <div><dt>Ekspozycja</dt><dd>${formatNumber(trail.exposure)}</dd></div>
            <div><dt>Kondycja</dt><dd>${formatNumber(trail.condition)}</dd></div>
        </dl>
        <p class="position">${index + 1} z ${trails.length}</p>
    `;
}

function valueOf(id) {
    return document.querySelector("#" + id).value;
}

function formatNumber(value) {
    const number = Number(value);
    return Number.isInteger(number) ? String(number) : number.toFixed(1);
}

function escapeHtml(value) {
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;");
}
