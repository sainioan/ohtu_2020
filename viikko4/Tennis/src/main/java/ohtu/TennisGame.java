package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private String score = "";

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String evenScore() {
        switch (player1Score) {
            case 0:
                score = "Love-All";
                return score;

            case 1:
                score = "Fifteen-All";
                return score;
            case 2:
                score = "Thirty-All";
                return score;
            case 3:
                score = "Forty-All";
                return score;
            default:
                score = "Deuce";
                return score;

        }

    }
    public void nonEvenScore(){
         int tempScore = 0;
                    for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = player1Score;
            } else {
                score += "-";
                tempScore = player2Score;
            }
            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }
    }
    public void scoreAboveForty(){
                    int scoreDifference = player1Score - player2Score;
        if (scoreDifference == 1) {
            score = "Advantage player1";
        } else if (scoreDifference == -1) {
            score = "Advantage player2";
        } else if (scoreDifference >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
    }
    public String getScore() {

       
        if (player1Score == player2Score) {
            evenScore();
        }
    
    else if (player1Score >=4 || player2Score >=4){
        scoreAboveForty();
//            int scoreDifference = player1Score - player2Score;
//        if (scoreDifference == 1) {
//            score = "Advantage player1";
//        } else if (scoreDifference == -1) {
//            score = "Advantage player2";
//        } else if (scoreDifference >= 2) {
//            score = "Win for player1";
//        } else {
//            score = "Win for player2";
//        }
    }

    
        else
        {
           
            nonEvenScore();

    }
    return score ;
}

}
