#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

// Define permute function
vector<vector<int>> permute(const vector<int>& input, int length) {
    vector<vector<int>> result;
    if (length == 1) {
        for (int i : input) {
            result.push_back({i});
        }
    } else {
        for (int i : input) {
            vector<int> remaining;
            for (int num : input) {
                if (num != i) {
                    remaining.push_back(num);
                }
            }
            auto innerPermutations = permute(remaining, length - 1);
            for (const auto& innerPerm : innerPermutations) {
                vector<int> permutation = {i};
                permutation.insert(permutation.end(), innerPerm.begin(), innerPerm.end());
                result.push_back(permutation);
            }
        }
    }
    return result;
}

// Get all unique letters in the words
vector<char> getLetters(const string& firstWord, const string& secondWord, const string& resultWord) {
    unordered_map<char, bool> lettersMap;
    vector<char> letters;
    for (char c : firstWord) {
        lettersMap[c] = true;
    }
    for (char c : secondWord) {
        lettersMap[c] = true;
    }
    for (char c : resultWord) {
        lettersMap[c] = true;
    }
    for (const auto& pair : lettersMap) {
        letters.push_back(pair.first);
    }
    return letters;
}

// Get all possible numbers for the letters
vector<vector<int>> getNumbers(const vector<char>& letters) {
    vector<vector<int>> numbers;
    vector<int> digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    auto permutations = permute(digits, letters.size());
    for (const auto& perm : permutations) {
        numbers.push_back(perm);
    }
    return numbers;
}

// Check if the solution is correct
bool checkSolution(const string& firstWord, const string& secondWord, const string& resultWord, const unordered_map<char, int>& solution) {
    int firstNumber = 0;
    int secondNumber = 0;
    int resultNumber = 0;
    for (char c : firstWord) {
        firstNumber = firstNumber * 10 + solution.at(c);
    }
    for (char c : secondWord) {
        secondNumber = secondNumber * 10 + solution.at(c);
    }
    for (char c : resultWord) {
        resultNumber = resultNumber * 10 + solution.at(c);
    }
    return firstNumber + secondNumber == resultNumber;
}

// Get all possible solutions
vector<unordered_map<char, int>> getSolutions(const string& firstWord, const string& secondWord, const string& resultWord, const vector<char>& letters, const vector<vector<int>>& numbers) {
    vector<unordered_map<char, int>> solutions;
    for (const auto& number : numbers) {
        unordered_map<char, int> solution;
        for (size_t i = 0; i < letters.size(); ++i) {
            solution[letters[i]] = number[i];
        }
        if (checkSolution(firstWord, secondWord, resultWord, solution)) {
            solutions.push_back(solution);
        }
    }
    return solutions;
}

int main() {
    string firstWord, secondWord, resultWord;

    cout << "Enter the first word: ";
    cin >> firstWord;
    cout << "Enter the second word: ";
    cin >> secondWord;
    cout << "Enter the result word: ";
    cin >> resultWord;

    // Get all unique letters in the words
    auto letters = getLetters(firstWord, secondWord, resultWord);
    // Get all possible numbers for the letters
    auto numbers = getNumbers(letters);
    // Get all possible solutions
    auto solutions = getSolutions(firstWord, secondWord, resultWord, letters, numbers);

    // Print solutions
    cout << "Total solutions found: " << solutions.size() << (solutions.size() ? "!" : "🥺") << endl;
    for (size_t i = 0; i < solutions.size(); ++i) {
        cout << "Solution " << i + 1 << ":\n";
        for (const auto& pair : solutions[i]) {
            cout << pair.first << ": " << pair.second << endl;
        }
        cout << "-----\n";
    }

    return 0;
}
