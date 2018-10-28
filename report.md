Title: Todo-list WebApp
Subtitle: Java EE - Project report
Author: MILIANI Amine - IMMACOLATO Nathan
Date: 2018.10.28

***

# Introduction

The goal of this project was to use the work done in the previous sessions, as well as new information to search for ourselves, and apply in to create a simple todo list web app needing login.

# What was done

The project was described in sections, and we will go through each one.

## Student login

A database holds a table of users, part of it being students.
The login page is shared among all users, and if the user loging in is a student, he is redirected to a page listing all todos.

## Instructor login

Following the student login, if an instructor logs in, he is redirected to a similar page listing all todos, but with a few more functionalities that we will describe later down.

## Listing todos

The instructor and student page are similar in the listing of the todos.
Those are held in a table of todos, in the same database as the users.
The information is displayed in a simple visual table, descriptions of each todo taking each a new line.
The student displaying only dispays that table and a button to log out.

## Adding a todo

The instructor is allowed to create a new todo and write a description for it.
On the main instructor page listing the todos, the user can press a button to be directed to another page.

This page holds a field to write the description of the new todo in.
A *submit* button then adds a new line in the todos table with the right description.
A *back to list* button sends the user back to the previous page.

## Editing a todo

The editing is similar to the **Adding a todo** page.
Next to each todo in the instructor's listing page, there is an *edit* button, sending the user to another page.
That page has a field to write the new description in and a *submit* button to talk to the database and change the description of the right todo.
A *back to list* button sends the user back to the previous page.

## Removing a todo

Next to each *edit* button, there is a *delete* button.
When pressed, it talks to the database and removes the right todo from the table.
The page is then refreshed, to she the up to date information.

## Sessions

When a user logs in, we store his name in a session and use it to display it on every page.
A user can log out by pressing a *log out* button, or going back to the login page, where the currrent session is destroyed.

## Cookies

When a user logs in, we store his name in a cookie on the browser.
Each time the user loads the login page, a session is created and, if a cookie exists, we use the information of the last name given to write in the *username* field.

# Issues faced

Working on the project, we faced a few issues.

## No DBUtil

The first idea, following the structure used in the previous sessions, was to use a DBUtil file where all methods for talking to the database would be written in.
We didn't manage to get that to work, requests wouldn't work in it, but they would when written among the rest of the code in the servlet.
So instead of having the servlet call methods in DBUtil to manipulate the database, all methods meant to be in it are written in the relevant servlet.

## Pecking code

On sessions and cookies, not having any knowledge on the subjects, we went to the internet for documentation, help and guides.
Our code works as far as we could tell, which seems good enough for the project's functionalities.
Most of that part was coded trying and seeing what happened, until something went somewhere and we started understanding and knowing what to expect with changes.


