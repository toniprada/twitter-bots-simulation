# twitter-bots-simulation

This project is a simulation of Twitter bots that runs on the multiagents framework [MASON](http://cs.gmu.edu/~eclab/projects/mason/).

The objective of this simulation is to test a _trained classifier_ in an original way. This _trained classifier_ identifies  what Twitter accounts are from **real persons** and which ones are not (companies, fansites, bots...). This is useful for example when coding a social network as _Google+_ that only wants real people with real profiles.

## How

This project simulates a lot of Twitter bots running at the same time, each one of them running as an agent. Each agent on the simulations has a profile and a behaviour when tweeting, following other accounts, etc. Each 1000 steps everyone of them stops to check its status with the classifier (human or not human).

## Why I can't run this project

Because you don't have acces to all the code.

- The classifier: it exists, but you don't have access to the code (sorry). You can code your own classifier and put it in a server, that's how each agent communicates with it.
- `/data/names`: List of real names, one at a line.
- `/data/sources`: List of tweets sources, one at a line.