# Grań

Katalog szlaków tatrzańskich: filtry trudności, szczegóły trasy, cruxy z filmem YouTube i linkiem do mapy turystycznej. Frontend to statyczny HTML/JS, backend to Spring Boot API.

## Stack

- Java 21, Spring Boot 4
- Spring Data JPA, Validation, Security
- PostgreSQL + Flyway
- Docker Compose (Postgres przy starcie aplikacji)

## Uruchomienie

Wymagane: Java 21, Docker.

```bash
./mvnw spring-boot:run
```

Aplikacja: [http://localhost:8080/](http://localhost:8080/)

Spring Boot sam podnosi Postgres z `compose.yaml` (docker-compose).

## Role

| Konto | Hasło | Uprawnienia |
|---|---|---|
| `admin@gran.pl` | `admin123` | przeglądanie + dodawanie / edycja / usuwanie szlaków i cruxów |
| `user@gran.pl` | `user123` | tylko przeglądanie |

Rejestracja (`/register`) tworzy konto z rolą `USER`.

## Strony

| URL | Opis |
|---|---|
| `/` | strona startowa |
| `/trails` | katalog z filtrami |
| `/trails/{id}` | szczegóły szlaku (+ zarządzanie cruxami dla admina) |
| `/trails/new`, `/trails/{id}/edit` | formularze szlaku (ADMIN) |
| `/cruxes/{id}` | szczegóły cruxa + film |
| `/login`, `/register` | logowanie / rejestracja |

## API (skrót)

**Publiczne (GET)**

- `GET /api/trails` — lista (filtry query: min/max distance, technical, exposure, condition)
- `GET /api/trails/{id}` — szlak + cruxy
- `GET /api/cruxes/{id}` — crux
- `GET /api/me` — stan sesji

**Auth**

- `POST /api/auth/register` — rejestracja USER
- `POST /login` / `POST /logout` — formularz Spring Security

**ADMIN**

- `POST/PUT/DELETE /api/trails...` — CRUD szlaków
- `POST /api/trails/{id}/cruxes` — dodaj crux
- `PUT /api/cruxes/{id}` — edytuj crux
- `DELETE /api/cruxes/{id}` — usuń crux

## Baza

Schemat i seed idą przez Flyway (`src/main/resources/db/migration`):

- `V1` — szlaki
- `V2` — cruxy
- `V3` — `youtube_id`
- `V4`–`V5` — `map_url`
- `V6` — `app_user` (role `USER` / `ADMIN`)

Nie edytuj już zastosowanych migracji — dodawaj kolejne `Vn`.

## Struktura

```text
src/main/java/pl/gran/gran/
  config/     SecurityConfig
  trail/      szlaki i cruxy (encje, repo, kontrolery)
  user/       konta, rejestracja, seed admin/user
src/main/resources/
  static/     HTML/CSS/JS
  db/migration/
```
