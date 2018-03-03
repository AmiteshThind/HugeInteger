/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main23;

import static java.lang.Character.isDigit;
import java.util.Random;

/**
 *
 * @author AmiteshThind
 */
public class HugeInteger {

    int length;
    int sign;
    Node head;
    Node tail;

    public HugeInteger(String val) {
        head = new Node(0, null, null);
        tail = new Node(0, null, head);
        head.next = tail;

        length = val.length();

        if (val.charAt(0) == '-') {
            this.sign = -1;

        } else if (val.charAt(0) - 48 == 0) {
            this.sign = 0;
        } else {
            this.sign = 1;
        }

        Node current = head;

        if (val.charAt(0) == '-') {

            for (int i = 1; i < this.length; i++) {

                if (isDigit(val.charAt(i))) {

                    Node node = new Node(val.charAt(i) - 48, current.next, current);
                    current.next = node;
                    tail.prev = node;
                    current = current.next;
                } else {

                    throw new IllegalArgumentException("Invalid Character");
                }

            }
        } else {
            for (int i = 0; i < this.length; i++) {
                if (isDigit(val.charAt(i))) {
                    Node node = new Node(val.charAt(i) - 48, current.next, current);
                    current.next = node;
                    tail.prev = node;
                    current = current.next;

                } else {

                    throw new IllegalArgumentException("Invalid Character");
                }
            }
        }

        this.length = (this.sign == -1 ? this.length - 1 : this.length);

    }

    public HugeInteger(int n) {

        head = new Node(0, null, null);
        tail = new Node(0, null, head);
        head.next = tail;
        this.length = n;
        this.sign = 1;
        Random Rand = new Random();
        Node current = this.head;

        for (int i = 0; i < this.length; i++) {
            Node node = new Node(0, tail, current);
            if (i == 0) {
                node.data = Rand.nextInt(10) + 1;
            } else {
                node.data = Rand.nextInt(10);
            }

            current.next = node;
            tail.prev = node;
            current = current.next;
        }
    }

    public HugeInteger add(HugeInteger h) {

        int BigLength = (this.length >= h.length ? this.length : h.length);
        int SmallLength = (this.length < h.length ? this.length : h.length);
        int LengthDiff = BigLength - SmallLength;
        int indexsum = 0, carry = 0, temp = 0;
        String sum = "";
        //232+23
        // add zeroes to smaller length 
        if (this.length >= h.length) {
            Node current = h.head;
            for (int i = 0; i < LengthDiff; i++) {

                Node filler = new Node(0, current.next, current);
                filler.next.prev = filler;
                current.next = filler;
                current = filler;
            }
        } else if (this.length < h.length) {
            Node current = this.head;
            for (int i = 0; i < LengthDiff; i++) {

                Node filler = new Node(0, current.next, current);
                filler.next.prev = filler;
                current.next = filler;
                current = filler;

            }

        }

        if (this.sign == h.sign) {

            Node hTail = h.tail;
            Node thisTail = this.tail;
            for (int i = 0; i < BigLength; i++) {
                indexsum = hTail.prev.data + thisTail.prev.data + carry;

                if (i != BigLength - 1) {
                    if (indexsum > 9) {

                        temp = indexsum % 10;
                        carry = indexsum / 10;

                    } else {

                        temp = indexsum;
                        carry = 0;

                    }

                    sum = temp + sum;

                    hTail = hTail.prev;
                    thisTail = thisTail.prev;

                } else if (i == BigLength - 1) {

                    sum = indexsum + sum;

                }

            }

        } else if (this.sign == 1 && h.sign == -1) {

            // figure out what the sign will be 
            int counter = 0;
            h.sign = 1;
            HugeInteger add = this.subtract(h);
            for (int i = 0; i < BigLength; i++) {
                if (this.head.next.data > h.head.next.data) {
                    add.sign = this.sign;
                    return add;

                } else if (h.head.next.data > this.head.next.data) {
                    add.sign = -1;
                    return add;

                } else {
                    this.head = this.head.next;
                    h.head = h.head.next;
                    counter++;
                }
            }
            if (counter == BigLength) {
                add.sign = 0;
                return add;
            }
            h.sign = -1;

        } else if (this.sign == -1 && h.sign == 1) {

            // figure out what the sign will be 
            int counter = 0;
            this.sign = 1;
            HugeInteger add = this.subtract(h);
            for (int i = 0; i < BigLength; i++) {
                if (this.head.next.data > h.head.next.data) {
                    add.sign = -1;
                    return add;

                } else if (h.head.next.data > this.head.next.data) {
                    add.sign = h.sign;
                    return add;

                } else {
                    this.head = this.head.next;
                    h.head = h.head.next;
                    counter++;
                }
            }
            if (counter == BigLength) {
                add.sign = 0;
                return add;
            }
            h.sign = -1;

        } else if (this.sign == 0) {
            return h;

        } else if (h.sign == 0) {
            return this;
        }

        HugeInteger x = new HugeInteger(sum);
        if (this.sign == 1 && h.sign == 1) {
            x.sign = 1;
        } else if (this.sign == -1 && h.sign == -1) {
            x.sign = -1;
        }

        return x;

    }

    public HugeInteger subtract(HugeInteger h) {

        int BigLength = (this.length >= h.length ? this.length : h.length);
        int SmallLength = (this.length < h.length ? this.length : h.length);
        int LengthDiff = BigLength - SmallLength;
        int indexdiff = 0;
        String diff = "";
        boolean thisIsLarger;
        //232+23

        if (this.length > h.length) {
            thisIsLarger = true;

        } else {

            thisIsLarger = false;

        }

        // add zeroes to smaller length 
        if (this.length >= h.length) {
            Node current = h.head;
            for (int i = 0; i < LengthDiff; i++) {

                Node filler = new Node(0, current.next, current);
                filler.next.prev = filler;
                current.next = filler;
                current = filler;
            }
        } else if (this.length < h.length) {
            Node current = this.head;
            for (int i = 0; i < LengthDiff; i++) {

                Node filler = new Node(0, current.next, current);
                filler.next.prev = filler;
                current.next = filler;
                current = filler;

            }

        }

        if (this.sign == 1 && h.sign == -1) {

            HugeInteger difference;
            h.sign = 1;
            difference = this.add(h);
            h.sign = -1;
            difference.sign = 1;
            return difference;

        } // second case -ve - +ve == +ve + +ve
        else if (this.sign == -1 && h.sign == 1) {

            HugeInteger difference;
            this.sign = 1;
            difference = h.add(this);
            this.sign = -1;
            difference.sign = -1;
            return difference;

        } // 3rd case +ve - +ve
        else if (this.sign == 1 && h.sign == 1 || this.sign == -1 && h.sign == -1) {

            Node hTail = h.tail;
            Node thisTail = this.tail;

            if (this.length == h.length && (this.head.next.data == h.head.next.data)) {

                hTail = this.tail;
                thisTail = h.tail;

            }

            if (this.head.next.data >= h.head.next.data) {

                for (int i = 0; i < BigLength; i++) {

                    if (thisTail.prev.data >= hTail.prev.data) {

                        indexdiff = thisTail.prev.data - hTail.prev.data;

                    } else {
                        if (i != BigLength - 1) {
                            thisTail.prev.prev.data -= 1;
                            thisTail.prev.data += 10;
                            indexdiff = thisTail.prev.data - hTail.prev.data;
                        } else {
                            indexdiff = thisTail.prev.data - hTail.prev.data;
                        }
                    }

                    diff = indexdiff + diff;

                    hTail = hTail.prev;
                    thisTail = thisTail.prev;

                }

                HugeInteger difference = new HugeInteger(diff);

                if (this.compareTo(h) == 1) {
                    difference.sign = 1;
                } else if (this.compareTo(h) == -1) {
                    difference.sign = -1;
                }

                return difference;

            } else if (h.head.next.data >= this.head.next.data) {

                for (int i = 0; i < BigLength; i++) {

                    if (hTail.prev.data >= thisTail.prev.data) {

                        indexdiff = hTail.prev.data - thisTail.prev.data;

                    } else {
                        if (i != BigLength - 1) {
                            hTail.prev.prev.data -= 1;
                            hTail.prev.data += 10;
                            indexdiff = hTail.prev.data - thisTail.prev.data;
                        } else {
                            indexdiff = hTail.prev.data - thisTail.prev.data;
                        }
                    }

                    diff = indexdiff + diff;
                    hTail = hTail.prev;
                    thisTail = thisTail.prev;

                }
            } else if (h.head.next.data == this.head.next.data || h.length == this.length) {

                for (int i = 0; i < BigLength; i++) {

                    if (hTail.prev.data >= thisTail.prev.data) {

                        indexdiff = hTail.prev.data - thisTail.prev.data;

                    } else {
                        if (i != BigLength - 1) {
                            hTail.prev.prev.data -= 1;
                            hTail.prev.data += 10;
                            indexdiff = hTail.prev.data - thisTail.prev.data;
                        } else {
                            indexdiff = hTail.prev.data - thisTail.prev.data;
                        }
                    }
                    indexdiff = 10 - indexdiff;
                    diff = indexdiff + diff;
                    hTail = hTail.prev;
                    thisTail = thisTail.prev;

                }

            }

        }

        HugeInteger x = new HugeInteger(diff);
        if (this.compareTo(h) == 1) {
            x.sign = 1;
        } else if (this.compareTo(h) == -1) {
            x.sign = -1;
        }
        return x;

    }

    public String toString() {

        Node current = head.next;
        int leadingzeroes=0;
        for(int i=0;i<this.length;i++){
        if(current.data==0){
            leadingzeroes++;
        current=current.next;
        }
        else{
        break;
        }
        
        }
        
        
        
        String x = "";
        for (int i = 0; i < this.length-leadingzeroes; i++) {

            x += current.data;
            current = current.next;

        }

        if (this.sign == -1) {
            x = "-" + x;
        }
        return x;

    }

    public int compareTo(HugeInteger h) {

        int counter = 0;
        if (this.sign == 1 && h.sign == -1 || this.sign == 0 && h.sign == -1) {
            return 1;
        } else if (h.sign == 1 && this.sign == -1 || h.sign == 0 && this.sign == -1) {
            return -1;
        } else if (this.sign == 0 && h.sign == 0) {
            return 0;
        } else if (this.sign == 1 && h.sign == 1) {
            if (this.length > h.length) {
                return 1;
            } else if (h.length > this.length) {
                return -1;
            } else if (this.length == h.length) {
                for (int i = 0; i < this.length; i++) {
                    if (head.next.data > h.head.next.data) {
                        return 1;
                    } else if (head.next.data < h.head.next.data) {
                        return -1;
                    } else if (head.next.data == h.head.next.data) {
                        counter++;
                    }

                    head = head.next;
                    h.head = h.head.next;

                }
                if (counter == this.length) {
                    return 0;
                }
            }
        } else if (this.sign == -1 && h.sign == -1) {
            if (this.length > h.length) {
                return -1;
            } else if (h.length > this.length) {
                return 1;
            } else if (this.length == h.length) {
                for (int i = 0; i < this.length; i++) {
                    if (head.next.data > h.head.next.data) {
                        return -1;
                    } else if (head.next.data < h.head.next.data) {
                        return 1;
                    } else if (head.next.data == h.head.next.data) {
                        counter++;
                    }

                    head = head.next;
                    h.head = h.head.next;

                }
                if (counter == this.length) {
                    return 0;
                }
            }
        }

        return 1;

    }

    public HugeInteger multiply(HugeInteger h) {
        int multiOfIndexes = 0, temp = 0, carry = 0;
        HugeInteger summation = new HugeInteger("1");
        summation.head.next.data = 0;

        String digits = "";
        if (h.sign == 0 || this.sign == 0) {
            HugeInteger product = new HugeInteger("0");
            return product;
        }

        if (this.length >= h.length) {

            Node hTail = h.tail;
            Node thisTail = this.tail;

            for (int i = 0; i < h.length; i++) {

                for (int j = 0; j < this.length; j++) {

                    multiOfIndexes = hTail.prev.data * thisTail.prev.data + carry;
                    if (multiOfIndexes > 9) {
                        if (j != this.length - 1) {
                            temp = multiOfIndexes % 10;
                            carry = multiOfIndexes / 10;
                        } else if (j == this.length - 1) {
                            temp = multiOfIndexes;
                            carry = 0;

                        }

                    } else {
                        temp = multiOfIndexes;
                        carry = 0;

                    }
                    digits = temp + digits;
                    thisTail = thisTail.prev;

                }
                if (i != 0) {
                    for (int k = 0; k < i; k++) {
                        digits = digits + "0";
                    }
                }

                HugeInteger temperarory = new HugeInteger(digits);
                summation = summation.add(temperarory);
                digits = "";
                hTail = hTail.prev;
                thisTail = this.tail;
            }

        } else if (this.length < h.length) {

            Node hTail = h.tail;
            Node thisTail = this.tail;

            for (int i = 0; i < this.length; i++) {

                for (int j = 0; j < h.length; j++) {

                    multiOfIndexes = hTail.prev.data * thisTail.prev.data + carry;
                    if (multiOfIndexes > 9) {
                        if (j != h.length - 1) {
                            temp = multiOfIndexes % 10;
                            carry = multiOfIndexes / 10;
                        } else {
                            temp = multiOfIndexes;
                            carry = 0;

                        }

                    } else {
                        temp = multiOfIndexes;
                        carry = 0;

                    }
                    digits = temp + digits;
                    hTail = hTail.prev;

                }
                if (i != 0) {
                    for (int k = 0; k < i; k++) {
                        digits = digits + "0";
                    }
                }

                HugeInteger temperarory = new HugeInteger(digits);
                summation = summation.add(temperarory);
                digits = "";
                thisTail = thisTail.prev;
                hTail = h.tail;
            }

        }

        if (this.sign == 1 && h.sign == 1) {
            summation.sign = 1;
        } else if (this.sign == 1 && h.sign == -1 || this.sign == -1 && h.sign == 1) {
            summation.sign = -1;
        }

        return summation;
    }

}
