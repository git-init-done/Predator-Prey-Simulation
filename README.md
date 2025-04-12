# Predator-Prey-Simulation
A Java-based simulation that models a simple predator-prey ecosystem on a 2D grid, featuring **Doodlebugs (X)** and **Ants (o)**. This project demonstrates core **Object-Oriented Programming (OOP)** principles and algorithmic behavior through dynamic interaction between species, governed by movement, breeding, and survival rules.

---

**1) Features: -**

-> **Grid-Based Ecosystem:** Simulates a 20x20 environment populated with ants and doodlebugs.
  
-> **Turn-Based Simulation:** Press Enter to advance simulation steps and observe ecosystem evolution.
  
-> **Behavioral Rules: -**

  - **Doodlebugs**:
    
    - Hunt adjacent ants and eat them.
      
    - Breed every 8 steps.
      
    - Starve and die if they don’t eat within 3 steps.
      
  - **Ants: -**
    
    - Move randomly to adjacent empty cells.
      
    - Breed every 3 steps if space is available.
      
-> **Dynamic Equilibrium:** Populations shift over time, with random outcomes like extinction or dominance.

---

**2) Concepts Demonstrated: -**

-> **Object-Oriented Programming (OOP): -**
  
  - Abstract `Organism` class with polymorphic behavior.
    
  - Inheritance for `Ant` and `Doodlebug` classes.
    
  - Encapsulation of movement, breeding, and starvation logic.
    
-> **Simulation Logic: -**

  - Turn-based processing of species.
    
  - Randomized movement and breeding.
    
  - Grid-based state tracking and printing.

---

**3) Grid Output Symbols: -**

.	Empty Cell

o	Ant

X	Doodlebug

---

**4) Example Output: -**

<img width="201" alt="Image" src="https://github.com/user-attachments/assets/40e2f9d8-08f0-420c-9f20-0e3427d3c391" />
