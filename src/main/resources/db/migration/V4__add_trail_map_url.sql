ALTER TABLE trail ADD COLUMN map_url VARCHAR(300);

UPDATE trail SET map_url = 'https://mapa-turystyczna.pl/route/etf' WHERE name = 'Orla Perć (Zawrat – Krzyżne)';
UPDATE trail SET map_url = 'https://mapa-turystyczna.pl/route/1dt9y' WHERE name = 'Szpiglasowa Przełęcz';
UPDATE trail SET map_url = 'https://mapa-turystyczna.pl/route/3r2jp' WHERE name = 'Czarny Staw Gąsienicowy';
UPDATE trail SET map_url = 'https://mapa-turystyczna.pl/route/s0mr' WHERE name = 'Dolina Kościeliska';
UPDATE trail SET map_url = 'https://mapa-turystyczna.pl/route/p6bz' WHERE name = 'Dolina Chochołowska';
