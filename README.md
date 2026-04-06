# Mensa Food Tracker

A Java desktop application for tracking your meals at the KIT Mensa — including nutritional values, costs, sustainability scores, and a personal meal history.

---

## Motivation

Eating at the Mensa every day is convenient, but I had no real overview of what I was actually consuming — nutritionally or financially. So I built this small tool to change that. It pulls the current menu directly from the KIT Studierendenwerk, lets me log what I ate, and shows me aggregated stats over time. Nothing fancy, but genuinely useful in day-to-day student life.

---

## Features

- **Daily menu view** – Browse the live menu for any available day (defaults to today)
- **Meal logging** – Add meals from the menu to your personal history with one click
- **Remove entries** – Clean up your history at any time
- **Meal history** – Date-sorted list of all logged meals with name, price, and nutritional details
- **Statistics dashboard** with four panels:
  - **Cumulative totals** – kcal, protein, carbs, fat, total cost, veggie ratio
  - **Per-meal averages** – switchable calculation basis
  - **Dietary profile** – breakdown by meal category
  - **Sustainability score** – CO₂ estimate per meal
- **Switchable themes** – Nimbus, Metal, Motif via the menu bar
- **Persistent history** – Auto-saved to CSV on exit, reloaded on next launch

---

## Architecture

Built with a **MVC structure**, separating data, logic, and presentation:

```
src/
├── Application/        # Entry point (Main)
├── Controller/         # Event handling and coordination between Model and View
├── Model/              # Data classes, business logic, API access
└── View/               # Swing/AWT GUI components
    ├── DailyMenu/      # Date selector and daily menu display
    ├── History/        # Meal history list and removal UI
    └── Statistics/     # Statistics panels (cumulative, averages, profile, sustainability)
```

### Class Overview

**Application**
| Class | Description |
|---|---|
| `Main` | Entry point — contains the `main` method |

**Controller**
| Class | Description |
|---|---|
| `MainController` | Top-level controller, wires up all sub-controllers |
| `DailyMenuController` | Handles menu fetch and date selection logic |
| `HistoryController` | Manages adding/removing meals and updating the history view |
| `StatisticsController` | Fetches statistics data and updates the statistics view |
| `FileController` | Handles CSV read/write; registers the window close listener |
| `MenuController` | Handles the menu bar (Look & Feel switching) |

**Model**
| Class | Description |
|---|---|
| `MensaModel` | Wraps the `KITMensaScraper` API |
| `HistoryModel` | Stores and manages the meal history (`HistoryEntry` list) |
| `HistoryModel.HistoryEntry` | Inner class representing one history entry; `toString()` serializes to CSV |
| `StatisticsModel` | Computes aggregated statistics over the history |
| `CalculationType` | Enum for tracked metrics: `KCAL`, `PROTEINS`, `CARBS`, `FAT`, `PRICE` |
| `SustainabilityScoreType` | Enum for sustainability metrics (e.g. `CO2EMISSIONS`) |

**View**
| Class | Description |
|---|---|
| `MainView` | Root `JFrame` containing all panels |
| `Panel` | Interface prescribing an `initialize()` method for all panels |
| `Style` | Central font constants (`MAIN_FONT`, `BOLD_FONT`, `BOLD_ITALIC_FONT`) |
| `OptionPane` | Wrapper for info/error dialogs |
| `LookAndFeelType` | Enum for switchable themes: `NIMBUS`, `METAL`, `MOTIF` |

**View.DailyMenu**
| Class | Description |
|---|---|
| `DailyMenuPanel` | Root panel for the daily menu section |
| `DatePanel` | Date picker and refresh button |
| `MealListPanel` | Container for all `MealPanel` rows |
| `MealPanel` | One row per meal — details, add-to-history and additives buttons |

**View.History**
| Class | Description |
|---|---|
| `HistoryPanel` | Root panel for the history section |
| `HistoryHeaderPanel` | Column headers for the history list |
| `HistoryCenterPanel` | Scrollable container embedding all `HistoryListPanel`s |
| `HistoryListPanel` | Groups entries by date |
| `HistoryListEntryPanel` | One row per history entry, including remove button |

**View.Statistics**
| Class | Description |
|---|---|
| `CumulativesPanel` | Totals: kcal, protein, carbs, fat, cost, veggie ratio |
| `AveragesPanel` | Per-meal averages with selectable calculation basis |
| `ProfilePanel` | Meal category breakdown and veggie ratio |
| `SustainabilityPanel` | CO₂ and sustainability score overview |

---

## Requirements

- **Java 21** or newer
- **mensascraper-lib.jar** (see below)
- Active internet connection

---

## External Library

Menu data is fetched via the open-source [`kit-mensa-scraper`](https://github.com/muety/kit-mensa-scraper) library.

- **Download:** https://github.com/muety/kit-mensa-scraper/releases/latest
- **Docs:** https://docs.muetsch.io/kit-mensa-scraper

To add it in Eclipse: right-click the project → *Build Path* → *Add External Archives...* → select the `.jar`.

---

## Getting Started

1. Clone the repository
2. Open in Eclipse (or any Java IDE)
3. Add `mensascraper-lib.jar` to the build path
4. Run `Main.java`

On first launch the history is empty. It will be saved automatically as `history.csv` in the project root when you close the app.

---

## Data Format

History is persisted as a semicolon-delimited CSV file:

```
name;date;price;kcal;protein;carbs;fat;type
Pasta in Tomatensoße mit Speck;2025-06-10;3.2;943.0;35.0;110.0;28.0;PORK
Pasta in Tomaten - Broccolisoße;2025-06-10;3.2;856.0;30.0;98.0;22.0;VEGETARIAN
```

---

## Notes

- Only **today's and future** dates are available via the API (historical menus cannot be retrieved)
- Only **german** available as a language option
- API and file errors are surfaced to the user via dialogs
- The UI stays fully usable at any window size
- **JavaDoc** available for most of the methods for further information

---

## Author

**Julian Steinhauser**
