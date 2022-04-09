Compilado usando Java Development Kit 17 (pacote jdk-openjdk no linux)

O programa foi compilado e executado no terminal (bash).

Para compilar:

```
javac Main.java Board.java Search.java
```

Para executar: 

```
java Main <arg> [input]
```

- arg é um inteiro de 0-4 para selecionar o método de busca a ser usado.
    - 0: Busca em BFS
    - 1: Busca em IDFS
    - 2: Busca em A*
    - 3: Busca em Greedy - Manhattan
    - 4: Busca em DFS
- o input é lido do standard input, podendo ser escrito na linha de comando como uma sequência de 32 inteiros, separados por espaços ou newlines
- assim, pode ser invocado redirecionando, para leitura de ficheiro contendo o input
    
    Exemplo
    
    ```
    java Main 2 < input.txt
    ```
    

Ambiente: 

```jsx
System:
  Kernel: 5.16.16-arch1-1 x86_64
  Distro: EndeavourOS
Machine:
  Type: Laptop System: HP
CPU:
  Info: quad core model: Intel Core i7-8550U bits: 64 type: MT MCP cache:
    L2: 1024 KiB
  Speed (MHz): avg: 3859 min/max: 400/4000 cores: 1: 3934 2: 3869 3: 3965
    4: 3969 5: 3884 6: 3808 7: 4000 8: 3448

Java:
				Name           : jdk-openjdk
				Version         : 17.0.3.u3-1
				Description     : OpenJDK Java 17 development kit
```