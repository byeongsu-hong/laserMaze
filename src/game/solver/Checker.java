package game.solver;

import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
    private int[][] defaultPlacement;
    private int[][] lasorPlacement = {{0}, {0}, {0}, {0}, {0}}, resultPlacement = null;
    private int startFixedStatus; //2 : 블록 고정됨, 1 : 랜덤 블록, 0 : 블록 없음.
    private HashMap<Integer, Integer> randomBlockCount, fixedRandomBlockLocation;
    private int[] blockLocation = null;
    private int blockLocSize = 0;
    private int[] numberOfEachBlockSize = new int[]{0, 4, 2, 2, 4, 2, 4};
    private ArrayList<Integer> blockArray, blockMaxArray;
    private int goalNumber; //골의 개수
    //TODO : 고정된 랜덤 블럭의 위치를 파악할 것

    public void swap(Object arr[], int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public Checker(int[][] defaultPlacement, HashMap<Integer, Integer> randomBlockCount, int goalNumber) {
        this.defaultPlacement = defaultPlacement;
        this.randomBlockCount = randomBlockCount;
        startFixedStatus = isStartBlockInPlacement();
        fixedRandomBlockLocation = new HashMap<Integer, Integer>();
        this.goalNumber = goalNumber;

        int sum = 0;
        for(int key : randomBlockCount.keySet())
            sum += randomBlockCount.get(key);

        blockLocSize = sum;
        blockLocation = new int[sum];
        for(int i=0; i<sum; i++)
            blockLocation[i] = i;

        for(int i : randomBlockCount.keySet()) {
            for(int j=0; j<i; j++)
                blockArray.add(i);
        }

        //TODO : 랜덤 블록 HashMap 만들기

        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                if(defaultPlacement[i][j]%10 == 5) fixedRandomBlockLocation.put(i*5+j, defaultPlacement[i][j]/10);

     }

     //블록의 상태를 업데이트 해주는 코드
     public void changeLocationListStatus(int p, int[] blockLocation) {
        if(p >= 1 && blockLocation[p] >= 24) changeLocationListStatus(p-1, blockLocation);
        else {
            blockLocation[p]++;
            return;
        }
        blockLocation[p] = blockLocation[p-1] + 1;
     }

    public void changeBlockListStatus(int p, int[] blockStatus) {
        if(p >=1 && blockStatus[p] >= blockMaxArray.get(p)) changeBlockListStatus(p-1, blockStatus);
        else {
            blockStatus[p]++;
            return;
        }
        blockStatus[p] = 0;
    }

    //시작 블록이 정해져 있는가에 대한 여부.
    private int isStartBlockInPlacement() {
        int i, j;
        for(i = 0; i<5; i++) {
            for(j=0; j<5; j++) {
                if(defaultPlacement[i][j]/10 == 1) {
                    if(defaultPlacement[i][j]%10 == 5) return 1;
                    return 2;
                }
            }
        }
        return 0;
    }

    //결과가 잘 나오는지 체크하는 함수
    private boolean isTableValidate(ArrayList<Integer> questionBlockLocation, int[] questionBlockStatus) {
        //BlockLocation의 값
        //랜덤 지정된 블록의 종류
        //TODO : block의 Location에 기존 블록이 위치하는가 체크할 것

        int[][] statusBlock = defaultPlacement;
        for(int i=0; i<questionBlockLocation.size(); i++) {
            if(statusBlock[questionBlockLocation.get(i) / 5][questionBlockLocation.get(i) % 5] == 0) return false;
            statusBlock[questionBlockLocation.get(i) / 5][questionBlockLocation.get(i) % 5] = questionBlockStatus[i];
        }
        //시작 위치를 정해야 함
        //시작 위치 방향을 기준으로 레이저 돌려부리가~(기점별로 재귀함수 사용할것)

        //Find start point
        int sx=0, sy=0; //Start X, Start Y
        ArrayList<Integer> currentBlockLocation = new ArrayList<Integer>();
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++) {
                if (statusBlock[i][j] / 10 == 1) {
                    sx = i;
                    sy = j;
                }
                if (statusBlock[i][j] != 0) currentBlockLocation.add(i*5+j);
            }

        int[] mov = new int[]{0, 1};
        Block startBlock = new Block(sx*5+sy, statusBlock);
        startBlock.find();
        Validator validator = new Validator(currentBlockLocation.size(), goalNumber);
        startBlock.validate(validator);
        if(!validator.validate()) return false;
        //체크하는 과정 2 : 6X 블록에 모두 입력되었는가

        //체크하는 과정 3 : 이를 제외한 블록에 입력 되었는가
        //체크하 과정 4 : 총 타겟에 붙은 레이저의 개수는?

        resultPlacement = statusBlock;
        return true;
    }

    public boolean changeQuestionBlockType(Object[] arr) { //arr는 랜덤 블록의 컬러이다.
        //TODO: 각 색 블록의 Random 값을 받고, location은 order에 맞게 진행한다
        ArrayList<Integer> randomColorOrder = new ArrayList<Integer>();
        ArrayList<Integer> randomLocationOrder = new ArrayList<Integer>();
        blockMaxArray = new ArrayList<Integer>();

        //TODO: 뭔가 꼬임
        for(int i=0; i<arr.length; i++) {
            randomColorOrder.add((Integer) arr[i]);
            randomLocationOrder.add(blockLocation[i]);
            blockMaxArray.add(numberOfEachBlockSize[(Integer) arr[i]]);
        }

        int[] currentBlockOrder = new int[randomColorOrder.size()];

        while(true) {
            if(currentBlockOrder[currentBlockOrder.length-1] >= blockMaxArray.get(currentBlockOrder.length-1)) changeBlockListStatus(currentBlockOrder.length-1, currentBlockOrder);
            if(currentBlockOrder[currentBlockOrder.length-1] >= blockMaxArray.get(currentBlockOrder.length-1)) return false;
            if(isTableValidate(randomLocationOrder, currentBlockOrder)) return true;
            ++currentBlockOrder[currentBlockOrder.length-1];
        }
    }

    public boolean perm(Object[] arr, int pivot) {
        if (pivot == arr.length) {
            if (changeQuestionBlockType(arr)) return true;
            return false;
        }

        for (int i = pivot; i < arr.length; i++) {
            swap(arr, i, pivot);
            perm(arr, pivot + 1);
            swap(arr, i, pivot);
        }
        return false;
    }


    public int[][] run() {
        //블록의 위치를 변경하는 코드
        while(true) {
            blockLocation[blockLocSize-1]++;
            if (blockLocation[blockLocSize-1] > 24) changeLocationListStatus(blockLocSize-1, blockLocation);
            if (blockLocation[blockLocSize-1] >= 25) return null;
            //블록의 색을 섞는 코드
            Object[] arrayColorTemp = blockArray.toArray();
            if (perm(arrayColorTemp, 0)) {
                return resultPlacement;
            }
        }
    }

}
