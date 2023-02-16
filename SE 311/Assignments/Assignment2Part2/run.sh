#!/usr/bin/env bash

# Alias
alias alphabetizer="java -jar Alphabetizer/out/artifacts/Alphabetizer/Alphabetizer.jar"
alias circularshifter="java -jar CircularShifter/out/artifacts/CircularShifter/CircularShifter.jar"
alias input="java -jar Input/out/artifacts/Input/Input.jar"
alias noisewordremoval="java -jar NoiseWordRemoval/out/artifacts/NoiseWordRemoval/NoiseWordRemoval.jar"
alias output="java -jar Output/out/artifacts/Output/Output.jar"
alias searcher="java -jar Searcher/out/artifacts/Searcher/Searcher.jar"

# Run
input | noisewordremoval | circularshifter | alphabetizer | output
