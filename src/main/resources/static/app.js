const filters = [
    ["min-distance", "max-distance", "distance-value", "distance-fill", " km"],
    ["min-technical", "max-technical", "technical-value", "technical-fill", ""],
    ["min-exposure", "max-exposure", "exposure-value", "exposure-fill", ""],
    ["min-condition", "max-condition", "condition-value", "condition-fill", ""]
];

const grid = document.querySelector("#trail-grid");
const resultCount = document.querySelector("#result-count");
const searchButton = document.querySelector("#search");

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
    const trails = await response.json();
    trails.sort((a, b) => a.id - b.id);
    render(trails);
}

function render(trails) {
    resultCount.textContent = trailsLabel(trails.length);
    if (trails.length === 0) {
        grid.innerHTML = '<p class="empty">Żaden szlak nie pasuje do tych filtrów.</p>';
        return;
    }
    grid.innerHTML = trails.map(trailCard).join("");
}

function trailCard(trail) {
    return `
        <article class="trail-card">
            <div class="trail-photo" aria-hidden="true">
                <svg viewBox="0 0 640 220" preserveAspectRatio="xMidYMax slice">
                    <rect width="640" height="220" fill="#d5e0da"/>
                    <path d="M0 150 L100 80 L170 120 L260 48 L350 118 L440 70 L530 130 L640 78 L640 220 L0 220 Z" fill="#315246"/>
                    <path d="M0 180 L140 140 L250 175 L380 128 L500 168 L640 140 L640 220 L0 220 Z" fill="#1d332b"/>
                </svg>
            </div>
            <div class="trail-body">
                <p class="trail-level">${level(trail.technicalDifficulty)}</p>
                <h3><a href="/trails/${trail.id}">${escapeHtml(trail.name)}</a></h3>
                <p class="trail-start">Start: ${escapeHtml(trail.startPoint)}</p>
                <dl>
                    <div><dt>Dystans</dt><dd>${formatNumber(trail.distance)} km</dd></div>
                    <div><dt>Technika</dt><dd>${formatNumber(trail.technicalDifficulty)} / 5</dd></div>
                    <div><dt>Ekspozycja</dt><dd>${formatNumber(trail.exposure)} / 5</dd></div>
                    <div><dt>Kondycja</dt><dd>${formatNumber(trail.condition)} / 5</dd></div>
                </dl>
                <a class="details" href="/trails/${trail.id}">Zobacz szczegóły</a>
            </div>
        </article>
    `;
}

function level(value) {
    const number = Number(value);
    if (number >= 4) {
        return "Wymagający";
    }
    if (number >= 2.5) {
        return "Średni";
    }
    return "Łatwy";
}

function trailsLabel(count) {
    if (count === 1) {
        return "1 szlak";
    }
    const last = count % 10;
    const lastTwo = count % 100;
    if (last >= 2 && last <= 4 && (lastTwo < 12 || lastTwo > 14)) {
        return count + " szlaki";
    }
    return count + " szlaków";
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
