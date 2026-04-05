# LabTrack — Laboratory Sample Tracking System

LabTrack is a web application that helps laboratories manage patient samples from registration through to test results. It is built with Spring Boot, MySQL, and Thymeleaf.

## What this repository contains
This repository contains the full source code and design documents for the LabTrack capstone project.

## Technology Stack
- Java 17 / Spring Boot 3
- MySQL 8 / Spring Data JPA
- Thymeleaf / Bootstrap 5

## Key Features
- Register and track patient samples
- Enforce a one-directional status workflow (Received → In Progress → Completed)
- Auto-flag test results as HIGH, LOW, or NORMAL based on reference ranges
- Live dashboard with stat cards and search/filter
