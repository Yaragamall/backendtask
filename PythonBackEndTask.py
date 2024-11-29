import pg8000
import random
import csv
from datetime import timedelta, datetime, date
from faker import Faker

# Create Connection

mydb = {
    "database":"librarymanagementsystem",
    "user":"postgres",
    "password":"Yg37801760",
    "host":"localhost",
    "port":5432
    }

# Generate sample data
def generate_sample_data():
    fake = Faker()
    try:
        # connect to the database
        connection = pg8000.connect(**mydb)
        cursor = connection.cursor()
        print("Database connection successful!")

        # Generate 50 users
        for _ in range(50):
            name = fake.name()
            email = fake.email()
            role = random.choice(["Admin","Patron"])
            cursor.execute("INSERT INTO users(name,email,role) VALUES (%s,%s,%s)",(name,email,role))


        #Generate 100 books
        for _ in range(100):
            title = fake.sentence(nb_words=4).strip(".")
            author = fake.name()
            isbn = fake.isbn13().replace("-","")[:13]
            copies_avaialable = random.randint(1,10)
            cursor.execute("INSERT INTO books (title,author,isbn,copies_available) VALUES (%s,%s,%s,%s)",(title,author,isbn,copies_avaialable))

        #Generate random borrowing events
        cursor.execute("SELECT id FROM users")
        user_ids = [row[0] for row in cursor.fetchall()]
        cursor.execute("SELECT id FROM books")
        book_ids = [row[0] for row in cursor.fetchall()]

        for _ in range(200):
            user_id = random.choice(user_ids)
            book_id = random.choice(book_ids)
            borrow_date = fake.date_between(start_date="-1y",end_date="today")
            return_date = borrow_date + timedelta(days=random.randint(1,30)) if random.random() < 0.7 else None
            cursor.execute("INSERT INTO borrowing_history (user_id,book_id,borrow_date,return_date) VALUES (%s,%s,%s,%s)",(user_id,book_id,borrow_date,return_date))

        connection.commit()
        print("Sample data generated successfully!")
    except Exception as e:
        print(f"An error occurred: {e}")

    finally:
        if "cursor" in locals():
            cursor.close()
        if "connection" in locals():
            connection.close()

# Export books not borrowed in the last 6 months
def export_books_not_borrowed():
    # connect to the database
    try:
        connection = pg8000.connect(**mydb)
        cursor = connection.cursor()
        print("Database connection successful!")

        six_months_age = date.today() - timedelta(days=180)

        query = """
        SELECT b.title, b.author, b.isbn, b.copies_available
        FROM books b
        LEFT JOIN borrowing_history bh ON b.id = bh.book_id
        WHERE bh.borrow_date IS NULL OR bh.borrow_date < %s
        """

        cursor.execute(query,(six_months_age,))
        rows = cursor.fetchall()

        # Export to CSV

        with open("unborrowed_books.csv",mode="w",newline="") as file:
            writer = csv.writer(file)
            writer.writerow(["Title","Author","ISBN","copies Available"])
            writer.writerows(rows)

            print("Books not borrowed in the last 6 months exported to 'unborrowed_books.csv'.")

    except Exception as e:
        print(f"An error occurred: {e}")

    finally:
        if "cursor" in locals():
            cursor.close()
        if "connection" in locals():
            connection.close()

if __name__ == '__main__':
    generate_sample_data()
    export_books_not_borrowed()

