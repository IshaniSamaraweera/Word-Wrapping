# Word-Wrapping
A paragraph is an ordered list of n words, where word wi is li letters long. Want to divide the paragraph into a sequence of lines, each containing at most L letters. (No word is more than L letters long). Word processors are faced with the task of deciding where to place line breaks. Want to keep lines relatively balanced so that you don’t have some lines with lots of text and other shorter lines with little text but naturally there is an upper limit to the width of a line that cannot be exceeded due to the reasons such as margin, page size, etc.

Objective for this java project is to solve this problem of word wrapping (where to place line breaks) using a dynamic programming approach.

1. Input
The input will consist of a single integer M followed by some text. Will read all input from STDIN until the end-of file is reached. Let M denote the maximum width of each line. Can assume M is an integer between 1 and 100 inclusive. Let n be the number of words you have read from input (you are not told n in advance, have to keep track of how many words you read from STDIN). Can assume that n will be an integer between 1 and 30000.

2. Example
A word is a white-space delimited string of characters. Thus the following text:
Oh my! I had been there about 5 times. 
Contains 7 words which are the following:
“Oh”, “my!”, “I”, “had”, “been”, “there”, “about” “5” “times.”
Notice how the punctuation is included with each word. You should not necessarily assume any bound on the size of a word (except that it will be less than M so that you don’t have to hyphenate a word to allow it to cross line boundaries).

3. Idea
Let us assume you place words wi,...wj  on a single line where ( j >i ). You will need  spaces for the words where  is the length of the kth word. Plus, there should be a space between each word on the line that adds additional  spaces. Thus, you use a total of  spaces for this line. This size is not to exceed M but should be as close to M as possible. We define an error term as the cube of the number of remaining spaces:

Need to tally the error term for each line of text to obtain an overall score for the whole formatting job. There is one exception. Because the last line need not be balanced, the error term for the very last line of text is always 0.

4. Output
This outputs two things. First output the text with the optimal line-break placement that found. After the text, on the very last line, output the total error for the whole formatting job (the sum of all line error terms).
Here is an example (taken from a literature text) of an optimal formatting job:
INPUT
15 She is happy but is a blue gal. I am all gone.
OUTPUT
She is happy
but is a blue
gal. I am all
gone.
43
