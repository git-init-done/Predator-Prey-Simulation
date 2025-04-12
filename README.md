
# 🧬 Predator-Prey Simulation in Java

Welcome to a dynamic, console-based **Predator-Prey Simulation** built with Java! This ecosystem simulation models the interaction between two species — **Ants (prey)** and **Doodlebugs (predators)** — evolving over time in a 2D grid environment.

Prepare for a battle of survival, reproduction, and starvation as nature unfolds one turn at a time.

---

## 🌟 Key Features

- 🐜 **Ant Behavior** – Moves randomly, breeds every 3 steps  
- 🕷️ **Doodlebug Behavior** – Hunts ants, breeds every 8 steps, starves after 3 turns without food  
- 🧠 **Smart Movement Logic** – Each organism makes independent, rule-based decisions  
- ♻️ **Ecosystem Equilibrium** – Watch population balances and survival dynamics in action  
- 📉 **Real-Time Console Output** – Visualize evolving species in a character grid  

---

## 🔍 How It Works

### 🧩 Organism Types

- **Ant (`o`)**
  - Moves to a random adjacent empty space
  - Breeds every 3 steps if space permits

- **Doodlebug (`X`)**
  - Prioritizes adjacent ants to eat
  - Breeds every 8 steps
  - Dies (starves) after 3 steps without food

### 🎮 Simulation Loop

1. Grid is initialized with random ants and doodlebugs  
2. Each time step:
   - Doodlebugs move and attempt to eat
   - Doodlebugs breed or die based on behavior rules
   - Ants move and attempt to breed
3. Console output shows the updated state
4. Press Enter to advance the simulation

---

## 🚀 Getting Started

### 🛠 Requirements

- Java Development Kit (JDK)

### ⚙️ Compile & Run

```bash
javac simulation.java
java simulation
```

> Runs a 20x20 world with 100 ants and 5 doodlebugs by default.

---

## 📺 Sample Output

```
....................
....o..o..X.o.o.o...
...o.....o..o.X..o..
....X..o.o..X.......
...o....o......o....
....................
Press Enter to continue...
```

---

## 🔮 Future Improvements

- 📊 Add simulation statistics (population trends, extinction events)  
- 🎨 GUI-based visualization for a richer experience  
- 🧠 Smarter pathfinding using A* or greedy search  
- 🌐 World wrapping (toroidal grid behavior)  

---

## 📜 License

This project is released under the **MIT License** — free to modify, expand, and experiment with. Contributions welcome!

---

## 🧩 Final Thoughts

This simulation is a fun, hands-on exploration of natural selection, predator-prey dynamics, and autonomous agent behavior. Perfect for students, educators, or anyone interested in digital ecosystems.

Let the wild run wild. 🌿🕷️🐜
