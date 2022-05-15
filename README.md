# Trabalho de Teoria dos Compiladores (DCC045-UFJF)

## Contribuidores

- André Reis
- Lucca Schroder

## Descrição

- PENDENTE

- O simulador encontra-se escrito em linguagem JAVA e é composto pelos seguintes arquivos:

<center>

|   Arquivo   |                                  Descrição                                  |
| :---------: | :-------------------------------------------------------------------------: |
| `process.h` |  Arquivo de cabeçalho para a declaração da API de manutenção de processos   |
| `process.c` |           Arquivo que implementa a API de manutenção de processos           |
|  `sched.h`  | Arquivo de cabeçalho para a declaração da API de escalonamento de processos |
|  `sched.c`  |          Arquivo com a implementação do escalonamento de processos          |
| `lottery.h` | Arquivo de cabeçalho para a declaração da API baseada no Lottery Scheduling |
| `lottery.c` | Arquivo com a implementação do escalonamento baseado em Lottery Scheduling  |
|  `main.c`   |                 Arquivo do programa principal do simulador                  |

</center>

- BLABLA

- BLABLA2

- As estruturas e funções a serem implementadas possuem explicação no arquivo de cabeçalho `lottery.h` . São elas:

```c
typedef struct lottery_params LotterySchedParams;
void lottInitSchedInfo(void);
void lottInitSchedParams(Process *p, void *params);
Process* lottSchedule(Process *plist);
int lottReleaseParams(Process *p);
int lottTransferTickets(Process *src, Process *dst, int tickets);
```

- PENDENTE

## Para compilar

- Como compilar para debug:

```bash
gcc -Wall -Wextra -std=c2x -g -ggdb -Iinclude -Llib src/*.c -o bin/escalonamento
```

- Como compilar sem debug:

```bash
gcc -std=c2x -Iinclude -Llib src/*.c -o bin/escalonamento
```

## Para executar

```bash
./bin/escalonamento
```

## INFORMAÇÕES

**PID** - identificador do processo
**STAT** - estado do processo (4 é pronto, 8 em execução, 2 bloqueado)
**PPID** - processo pai
**CPU** - tempo de uso de CPU
**Tckts** - qntd de tickets
**SS** - scheduling slot (posicao que o algoritmo pra esse processo da ocupando, identificador do algoritmo) no nosso caso é sempre 0, só vai ter o lottery scheduling
**PRV** - campo que indica quem é o processo anterior na lista de processos
**NXT** - campo que indica quem é o processo posterior na lista de processos

## IDEIAS GERAIS

<hr>

1. Verificar se processos bloqueados não estão sendo selecionados
2. Verificar se a proporção de tickets que um processo tem é proporcional ao tempo de uso de cpu (somente tickets elegiveis)
3. Verificar a qntd de tickets a ser transferidas, se o processo "pai" tem essa qntd e etc

<hr>

- BLABLABLA 2