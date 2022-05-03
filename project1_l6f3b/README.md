# Sharing Book Manager

## Proposal

In this application, books have three *status*: 

- Borrowed (the book is borrowed by someone else, so people need to wait a few days if they want this book)
- Available (people can borrow it if they want)
- Missing (the book is lost or broken)

All the books have their **book names**, **author names**, **location** and **status**,and 
people can find the books they want by using these imformation.

This application is for the readers who are willing to share their books or someone wants some books that cannot be 
found in normal library or the book is too expensive to the readers, so that a reader can easily find the book he wants 
and all readers can have more opportunity to read.

I'm interested in this project because I like reading, especially the paper books. However, I found that I just read 
one book one time and the books that I have read would likely be decoration of my home. I think this is a waste of book,
so I come up with a idea that we can collect the idle books and share them with each other, so that a reader can 
easily find the book he wants and all readers can have more opportunity to find the book they want.





#User Stories:
- As a user, I want to be able to search a book by using its book name or author name.
- As a user, I want to be able to know if a book is still available.
- As a user, I want to be able to know where the book is if its avaliable
- As a user, I want to be able to borrow a book.
- As a user, I want to be able to return a book.
- As a user, I want to be able to import new books.
- As a user, I want to be able to report the loss of a book.
- As a user, I want to be able to save my sharing library book list to file
- As a user, I want to be able to be able to load my sharing library book list from file 


## Phase 4: Task 3
In my UML design diagram, I have 15 classes in total and one interface,one abstract among
them. The abstract class named SubWindow and all sub-windows extend it. I think my diagram is 
logical and simple. However, I realize maybe I can simplify it by using an abstract for all windows instead
of an abstract for sub-windows, and in this way, the MainWindow class can be simpler, and the diagram 
may also be more organized.