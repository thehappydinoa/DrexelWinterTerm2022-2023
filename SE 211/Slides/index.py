# from gpt_index import SimpleDirectoryReader
from gpt_index import GPTSimpleVectorIndex

# print("Loading documents...")
# loader = SimpleDirectoryReader(input_dir=".")
# documents = loader.load_data()
# print("Loaded {} documents".format(len(documents)))

# print("Building index...")
# index = GPTSimpleVectorIndex(documents)
# print("Index built")

# print("Saving index...")
# index.save_to_disk("index.json")
# print("Index saved")

index = GPTSimpleVectorIndex.load_from_disk("index.json")
# response = index.query("What is Detailed Design?")
# print(response)

while True:
    user_input = input("Enter a query: ").strip()
    if user_input == "exit":
        print("Exiting...")
        break
    if user_input == "":
        continue
    summary = index.query(user_input)
    print(summary)
