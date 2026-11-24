-- Dystanse = w dwie strony (lub pętla) od parkingu / realnego startu, nie od samego celu.
-- Trudności: szacunek 1–5 (technika, ekspozycja, kondycja).

-- Istniejące: ujednolić do pełnego dnia z parkingu / Kuźnic
UPDATE trail
SET name = 'Orla Perć (cała, z Kuźnic)',
    start_point = 'Kuźnice',
    distance = 23.0,
    technical_difficulty = 5,
    exposure = 5,
    condition = 4.5
WHERE name = 'Orla Perć (Zawrat – Krzyżne)';

UPDATE trail
SET start_point = 'Palenica Białczańska',
    distance = 29.5,
    condition = 3.5
WHERE name = 'Szpiglasowa Przełęcz';

UPDATE trail
SET distance = 13.0,
    condition = 2.0
WHERE name = 'Czarny Staw Gąsienicowy';

INSERT INTO trail (name, start_point, distance, technical_difficulty, exposure, condition) VALUES
    -- Doliny i klasyka od Palenicy
    ('Dolina Pięciu Stawów', 'Palenica Białczańska', 18.0, 1.5, 1.0, 2.5),
    ('Morskie Oko', 'Palenica Białczańska', 18.6, 1.0, 1.0, 2.0),
    ('Morskie Oko (przez Dolinę Pięciu Stawów i Świstówkę)', 'Palenica Białczańska', 20.3, 2.5, 2.5, 3.5),
    ('Czarny Staw pod Rysami', 'Palenica Białczańska', 22.6, 1.5, 1.5, 2.5),

    -- Nosal
    ('Nosal', 'Kuźnice', 4.0, 2.5, 2.5, 2.0),

    -- Orla: drugi wariant logistyczny (start Kuźnice, meta Palenica)
    ('Orla Perć (cała, Kuźnice → Palenica)', 'Kuźnice', 24.8, 5.0, 5.0, 4.5),

    -- Giewont: warianty wejścia
    ('Giewont (z Kuźnic)', 'Kuźnice', 13.0, 3.0, 3.5, 3.0),
    ('Giewont (z Doliny Małej Łąki)', 'Dolina Małej Łąki', 14.0, 3.0, 3.5, 3.0),
    ('Giewont (z Doliny Strążyskiej)', 'Dolina Strążyska', 14.0, 3.0, 3.5, 3.0),

    -- Kasprowy
    ('Kasprowy Wierch (z Kuźnic)', 'Kuźnice', 15.0, 2.0, 2.0, 3.0),

    -- Zawrat: 2 warianty
    ('Zawrat (z Kuźnic)', 'Kuźnice', 17.8, 3.5, 3.5, 3.5),
    ('Zawrat (z Palenicy Białczańskiej)', 'Palenica Białczańska', 22.0, 3.5, 3.5, 3.5),

    -- Krzyżne: 2 warianty
    ('Krzyżne (z Kuźnic)', 'Kuźnice', 20.0, 3.0, 3.0, 3.5),
    ('Krzyżne (z Palenicy Białczańskiej)', 'Palenica Białczańska', 25.0, 3.0, 3.0, 3.5),

    -- Świnica: 2 warianty z Kuźnic
    ('Świnica (przez Zawrat, z Kuźnic)', 'Kuźnice', 21.0, 4.5, 4.5, 4.0),
    ('Świnica (przez Świnicką Przełęcz, z Kuźnic)', 'Kuźnice', 17.0, 3.5, 4.0, 3.5),

    -- Kozi i Granaty
    ('Kozi Wierch (z Palenicy Białczańskiej)', 'Palenica Białczańska', 19.8, 4.0, 4.0, 4.0),
    ('Zadni Granat (z Kuźnic)', 'Kuźnice', 17.0, 4.0, 4.0, 3.5),
    ('Skrajny Granat (z Kuźnic)', 'Kuźnice', 17.5, 4.0, 4.0, 3.5),

    -- Mięguszowieckie / Wrota
    ('Mięguszowiecka Przełęcz pod Chłopkiem', 'Palenica Białczańska', 28.0, 5.0, 5.0, 4.5),
    ('Wrota Chałubińskiego', 'Palenica Białczańska', 24.0, 3.5, 3.5, 3.5),

    -- Gęsia Szyja: 2 warianty startu
    ('Gęsia Szyja (z Wierchu Poroniec)', 'Wierch Poroniec', 9.0, 1.5, 1.5, 2.0),
    ('Gęsia Szyja (z Palenicy Białczańskiej)', 'Palenica Białczańska', 9.6, 1.5, 1.5, 2.0);
