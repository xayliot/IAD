from flask import Flask, request, jsonify
import g4f

app = Flask(__name__)


@app.route('/gpt', methods=['POST'])
def gpt():
    data = request.get_json()
    question = data['question']

    response = GPT(question)

    return jsonify({'answer': response})


# def GPT(question):
#     response = g4f.ChatCompletion.create(
#         model=g4f.models.gpt_35_turbo,
#         messages=[{"role": "user", "content": question}],
#         proxy="http://B5JZGE:jTqpn7@85.195.81.170:11424",
#     )
#     print(response)
#     return response

def GPT(question):
    response = g4f.ChatCompletion.create(
        model=g4f.models.gpt_4,
        messages=[{"role": "user", "content": question}],
        proxy="http://B5JZGE:jTqpn7@85.195.81.170:11424",
    )
    return response


if __name__ == '__main__':
    app.run(debug=True)
