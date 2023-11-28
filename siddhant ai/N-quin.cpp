#include <iostream> // Include for printing the board
#include <unordered_set> // Include for printing the board

using namespace::std;

class Solution {
private:
    unordered_set<int> cols;     //for Columns
    unordered_set<int> negDiag;  //for negative diagnals R-C
    unordered_set<int> posDiag;  //for positive diagnals R+C
    
    void printBoard(const vector<string>& board) {

        for (const string& row : board) {
            for(char c : row){
            cout << c << " " ;
            }
            cout << endl;
        }
        cout << endl;

    }

    void backtrack(int n, int row, vector<vector<string> >& res, vector<string>& board){
        if(row == n){
            res.push_back(board);
            printBoard(board); // Print the board
            return ; 
        }
        
        for(int col = 0; col < n; col++){   //Shifting through each col
            if( cols.find(col) != cols.end() or //if queen already placed in this col
                negDiag.find(row - col) != negDiag.end() or //if queen in negDiag
                posDiag.find(row + col) != posDiag.end()    //if queen in posDiag
              )
                continue;
            
            cols.insert(col);
            negDiag.insert(row - col);
            posDiag.insert(row + col);
            board[row][col] = '$';
            
            backtrack(n, row + 1, res, board);
            
            cols.erase(col);
            negDiag.erase(row - col);
            posDiag.erase(row + col);
            board[row][col] = 'o';
        }
    }
   
public:
    vector<vector<string> > solveNQueens(int n) {
        vector<vector<string> > res;
        vector<string> board(n, string(n,'o'));
        backtrack(n, 0, res, board);
        return res;
    }
};

int main() {
    Solution solution;
    cout << "enter number of QUEENS greater than 3:" << endl;
    int n;
    cin >> n;
    vector<vector<string> > solutions = solution.solveNQueens(n);
    return 0;
}
